package com.gzy.timer.data.network.http;

import com.gzy.timer.Constant;
import com.gzy.timer.Factory;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 网络请求的封装
 *
 * @author qiujuer Email:qiujuer@live.cn
 * @version 1.0.0
 */
public class Network {
    private static Network instance;
    private Retrofit retrofit;
    private OkHttpClient client;

    static {
        instance = new Network();
    }

    private Network() {
    }

    public static OkHttpClient getClient() {
        if (instance.retrofit != null)
            return instance.client;

        // 得到一个OkHttpClient
        instance.client = new OkHttpClient.Builder()
                // 给所有的请求添加一个拦截器
                .addInterceptor(chain -> {
                    Request original = chain.request();
                    // 重新进行build
                    Request.Builder builder = original.newBuilder();
//                        if (!TextUtils.isEmpty(Account.getToken())) {
//                            // 注入一个token
//                            builder.addHeader("token", Account.getToken());
//                        }
                    builder.addHeader("Content-Type", "application/json");
                    Request newRequest = builder.build();
                    return chain.proceed(newRequest);
                })
                .build();
        return instance.client;
    }

    // 构建一个Retrofit
    private static Retrofit getRetrofit() {
        if (instance.retrofit != null)
            return instance.retrofit;

        // 得到一个 OkHttpClient
        OkHttpClient client = getClient();

        instance.client = client;

        Retrofit.Builder builder = new Retrofit.Builder();

        instance.retrofit = builder.baseUrl(Constant.Http.API_URL)
                // 设置client
                .client(client)
                // 设置Json解析器
                .addConverterFactory(GsonConverterFactory.create(Factory.getGson()))
                .build();

        return instance.retrofit;
    }

    /**
     * 返回一个请求代理
     *
     * @return RemoteService
     */
    public static RemoteService remote() {
        return Network.getRetrofit().create(RemoteService.class);
    }
}
