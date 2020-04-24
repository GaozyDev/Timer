package com.timer.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.timer.R;
import com.timer.ui.base.BaseActivity;
import com.timer.ui.main.MainActivity;

/**
 * 登录页
 */
public class LoginActivity extends BaseActivity {

    private LoginViewModel loginViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        final Button loginButton = findViewById(R.id.login_btn);
        final ProgressBar loadingProgressBar = findViewById(R.id.login_loading_pb);

        loginViewModel.getLoginFormState().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState);
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                setResult(Activity.RESULT_OK);
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                loginViewModel.login("", "");
            }
        });
    }
}
