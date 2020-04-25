package com.gzy.timer.data.login;

import androidx.annotation.NonNull;

import com.gzy.timer.Factory;
import com.gzy.timer.R;
import com.gzy.timer.net.RspModel;
import com.gzy.timer.net.http.Network;
import com.gzy.timer.net.http.RemoteService;
import com.gzy.timer.net.model.login.LoginModel;
import com.gzy.timer.net.model.login.LoginRspModel;
import com.gzy.timer.net.model.login.QQModel;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginDataSource {

    public void login(String openId, String token, @NonNull DataSource.Callback<LoginRspModel> callback) {
        RemoteService service = Network.remote();
        QQModel qqModel = new QQModel(openId, token);
        LoginModel<QQModel> loginModel = new LoginModel<>(1, qqModel);
        Call<RspModel<LoginRspModel>> call = service.login(loginModel);
        call.enqueue(new Callback<RspModel<LoginRspModel>>() {
            @Override
            public void onResponse(@NotNull Call<RspModel<LoginRspModel>> call,
                                   @NotNull Response<RspModel<LoginRspModel>> response) {
                RspModel<LoginRspModel> rspModel = response.body();
                if (rspModel != null) {
                    if (rspModel.success()) {
                        LoginRspModel loginRspModel = rspModel.getResult();
                        callback.onDataLoaded(loginRspModel);
                    } else {
                        Factory.decodeRspCode(rspModel, callback);
                    }
                } else {
                    callback.onDataNotAvailable(R.string.app_name);
                }
            }

            @Override
            public void onFailure(@NotNull Call<RspModel<LoginRspModel>> call, @NotNull Throwable t) {
                callback.onDataNotAvailable(R.string.app_name);
            }
        });
    }

    void logout() {

    }
}
