package com.gzy.timer.ui.login;

class LoginResult {
    private String token;

    private String error;

    LoginResult(String token, String error) {
        this.token = token;
        this.error = error;
    }

    String getSuccess() {
        return token;
    }

    String getError() {
        return error;
    }
}
