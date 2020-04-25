package com.gzy.timer.ui.login;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

class LoginResult {
    @Nullable
    private String token;

    @Nullable
    @StringRes
    private Integer strRes;

    LoginResult(@Nullable Integer strRes) {
        this.strRes = strRes;
    }

    LoginResult(@Nullable String success) {
        this.token = success;
    }

    @Nullable
    String getSuccess() {
        return token;
    }

    @Nullable
    Integer getError() {
        return strRes;
    }
}
