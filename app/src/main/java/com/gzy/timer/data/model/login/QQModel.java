package com.gzy.timer.data.model.login;

/**
 * QQ 登录
 */
public class QQModel {
    private String openid;
    private String access_token;

    public QQModel(String openid, String access_token) {
        this.openid = openid;
        this.access_token = access_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
