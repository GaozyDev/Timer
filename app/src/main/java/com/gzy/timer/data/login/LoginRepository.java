package com.gzy.timer.data.login;

import com.gzy.timer.net.model.login.LoginRspModel;

public class LoginRepository {

    private static volatile LoginRepository instance;

    private LoginDataSource dataSource;

    private LoginRepository(LoginDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static LoginRepository getInstance(LoginDataSource dataSource) {
        if (instance == null) {
            instance = new LoginRepository(dataSource);
        }
        return instance;
    }

    public void login(String openId, String token, DataSource.Callback<LoginRspModel> callback) {
        dataSource.login(openId, token, callback);
    }

    public void logout() {
        dataSource.logout();
    }
}