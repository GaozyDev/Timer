package com.gzy.timer.net.http;

import com.gzy.timer.net.RspModel;
import com.gzy.timer.net.model.login.LoginModel;
import com.gzy.timer.net.model.login.LoginRspModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RemoteService {

    @POST("user/login")
    Call<RspModel<LoginRspModel>> login(@Body LoginModel loginModel);
}