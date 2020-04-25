package com.gzy.timer.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gzy.timer.data.login.DataSource;
import com.gzy.timer.data.login.LoginRepository;
import com.gzy.timer.net.model.login.LoginRspModel;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginRspModel> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<LoginRspModel> getLoginResult() {
        return loginResult;
    }

    public void login(String openId, String token) {
        loginRepository.login(openId, token, new DataSource.Callback<LoginRspModel>() {

            @Override
            public void onDataNotAvailable(int strRes) {
                loginResult.setValue(null);
            }

            @Override
            public void onDataLoaded(LoginRspModel loginRspModel) {
                loginResult.setValue(loginRspModel);
            }
        });
    }
}