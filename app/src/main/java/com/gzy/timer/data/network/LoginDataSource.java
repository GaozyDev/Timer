package com.gzy.timer.data.network;

import androidx.annotation.NonNull;

import com.gzy.timer.App;
import com.gzy.timer.R;
import com.gzy.timer.data.DataSource;
import com.gzy.timer.data.model.login.LoginModel;
import com.gzy.timer.data.model.login.LoginRspModel;
import com.gzy.timer.data.model.login.QQModel;
import com.gzy.timer.data.network.http.Network;
import com.gzy.timer.data.network.http.RemoteService;

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
        call.enqueue(new LoginRspCallback(callback));
    }

    public void logout() {

    }

    private static class LoginRspCallback implements Callback<RspModel<LoginRspModel>> {

        final DataSource.Callback<LoginRspModel> callback;

        LoginRspCallback(DataSource.Callback<LoginRspModel> callback) {
            this.callback = callback;
        }

        @Override
        public void onResponse(@NotNull Call<RspModel<LoginRspModel>> call,
                               @NotNull Response<RspModel<LoginRspModel>> response) {
            RspModel<LoginRspModel> rspModel = response.body();
            if (rspModel != null) {
                if (rspModel.success()) {
                    LoginRspModel loginRspModel = rspModel.getResult();
                    callback.onDataLoaded(loginRspModel);
                } else {
                    callback.onDataNotAvailable(rspModel.getMessage());
                }
            } else {
                callback.onDataNotAvailable(App.getInstance().getString(R.string.data_rsp_error_service));
            }
        }

        @Override
        public void onFailure(@NotNull Call<RspModel<LoginRspModel>> call, @NotNull Throwable t) {
            callback.onDataNotAvailable(App.getInstance().getString(R.string.data_rsp_error_service));
        }
    }
}
