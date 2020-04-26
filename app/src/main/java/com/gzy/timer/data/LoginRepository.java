package com.gzy.timer.data;

import com.gzy.timer.data.model.login.LoginRspModel;
import com.gzy.timer.data.network.LoginDataSource;

public class LoginRepository {

    private static volatile LoginRepository instance;

    private LoginDataSource mDataSource;

    private LoginRepository(LoginDataSource dataSource) {
        this.mDataSource = dataSource;
    }

    public static LoginRepository getInstance(LoginDataSource dataSource) {
        if (instance == null) {
            instance = new LoginRepository(dataSource);
        }
        return instance;
    }

    public void login(String openId, String token, DataSource.Callback<LoginRspModel> callback) {
        mDataSource.login(openId, token, callback);
    }

    public void logout() {
        mDataSource.logout();
    }
}
