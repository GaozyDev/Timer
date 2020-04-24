package com.timer.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.timer.data.LoginRepository;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<Boolean> loginFormState = new MutableLiveData<>();
    private MutableLiveData<Boolean> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<Boolean> getLoginFormState() {
        return loginFormState;
    }

    LiveData<Boolean> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String password) {
        boolean success = loginRepository.login(username, password);
        loginResult.setValue(success);
    }
}
