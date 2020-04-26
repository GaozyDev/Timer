package com.gzy.timer;

public class App extends android.app.Application {
    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    /**
     * 外部获取单例
     *
     * @return App
     */
    public static App getInstance() {
        return instance;
    }
}
