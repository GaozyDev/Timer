package com.gzy.timer.net.model.login;

/**
 * 用户登录
 */
public class LoginModel<T> {
    // 1 QQ 2 微信 3 手机
    private int type;
    private T account;

    public LoginModel(int type, T account) {
        this.type = type;
        this.account = account;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public T getAccount() {
        return account;
    }

    public void setAccount(T account) {
        this.account = account;
    }
}
