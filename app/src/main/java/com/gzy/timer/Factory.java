package com.gzy.timer;

import androidx.annotation.StringRes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gzy.timer.data.login.DataSource;
import com.gzy.timer.net.RspModel;

public class Factory {

    // 单例模式
    private static final Factory instance;
    // 全局的Gson
    private final Gson gson;

    static {
        instance = new Factory();
    }

    /**
     * 返回一个全局的Gson，在这可以进行Gson的一些全局的初始化
     */
    public static Gson getGson() {
        return instance.gson;
    }

    private Factory() {
        gson = new GsonBuilder()
                // 设置时间格式
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
                .create();
    }

    /**
     * 进行错误Code的解析，
     * 把网络返回的Code值进行统一的规划并返回为一个String资源
     *
     * @param model    RspModel
     * @param callback DataSource.FailedCallback 用于返回一个错误的资源Id
     */
    public static void decodeRspCode(RspModel model, DataSource.FailedCallback callback) {
        if (model == null)
            return;

        // 进行Code区分
        switch (model.getCode()) {
            case RspModel.SUCCEED:
                return;
            case RspModel.ERROR_SERVICE:
                decodeRspCode(R.string.app_name, callback);
                break;
            case RspModel.ERROR_ACCOUNT_LOGIN:
                decodeRspCode(R.string.app_name, callback);
                break;
            default:
                decodeRspCode(R.string.app_name, callback);
                break;
        }
    }

    private static void decodeRspCode(@StringRes final int resId,
                                      final DataSource.FailedCallback callback) {
        if (callback != null)
            callback.onDataNotAvailable(resId);
    }
}
