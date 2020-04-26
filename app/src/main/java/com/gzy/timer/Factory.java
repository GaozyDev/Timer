package com.gzy.timer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
}
