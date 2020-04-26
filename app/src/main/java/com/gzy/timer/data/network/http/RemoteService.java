package com.gzy.timer.data.network.http;

import com.gzy.timer.data.network.RspModel;
import com.gzy.timer.data.model.login.LoginModel;
import com.gzy.timer.data.model.login.LoginRspModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RemoteService {

    @POST("user/login")
    Call<RspModel<LoginRspModel>> login(@Body LoginModel loginModel);
}