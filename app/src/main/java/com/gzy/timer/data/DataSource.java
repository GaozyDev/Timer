package com.gzy.timer.data;

/**
 * Created by Sunny on 2017/5/26.
 * Email：670453367@qq.com
 * Description: TOOD
 */

public interface DataSource {

    /**
     * 同时包括了成功和失败的回调接口
     */
    interface Callback<T> extends SucceedCallback<T>, FailedCallback {

    }

    interface SucceedCallback<T> {
        void onDataLoaded(T t);
    }

    interface FailedCallback {
        void onDataNotAvailable(String error);
    }
}
