package com.gzy.timer.data.network;

public class RspModel<T> {

    public static final int SUCCEED = 20000;

    public static final int ERROR_SERVICE = 5001;

    public static final int ERROR_ACCOUNT_LOGIN = 2002;

    private int code;
    private String message;
    private T result;

    public boolean success() {
        return code == SUCCEED;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}