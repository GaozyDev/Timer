package com.gzy.timer;

/**
 * 常量
 */
public class Constant {

    /**
     * APP 相关参数
     */
    public interface App {
        String QQ_APP_ID = "101868875";
    }

    /**
     * Http 相关参数
     */
    public interface Http {
        // 开发环境
        String API_URL = "http://118.25.186.104:10001/";
    }

    /**
     * SharedPreferences key
     */
    public interface Sp {
        /**
         * QQ 用户唯一识别
         */
        String QQ_OPEN_ID = "qq_open_id";
        /**
         * QQ 登录 token
         */
        String QQ_ACCESS_TOKEN = "qq_access_token";
        /**
         * QQ 登录过期时间
         */
        String QQ_EXPIRES_IN = "qq_expires_in";
        /**
         * 用户登录 token
         */
        String TOKEN = "token";
    }
}
