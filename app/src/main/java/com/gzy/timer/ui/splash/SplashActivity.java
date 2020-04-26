package com.gzy.timer.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;

import com.gzy.timer.Constant;
import com.gzy.timer.R;
import com.gzy.timer.ui.BaseActivity;
import com.gzy.timer.ui.login.LoginActivity;
import com.gzy.timer.ui.main.MainActivity;
import com.gzy.timer.utils.SpUtils;

/**
 * 闪屏页
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        startTimer();
    }

    private void startTimer() {
        CountDownTimer countDownTimer = new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                String token = SpUtils.getString(SplashActivity.this, Constant.Sp.TOKEN, null);
                Intent intent = new Intent(SplashActivity.this,
                        TextUtils.isEmpty(token) ? LoginActivity.class : MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
        countDownTimer.start();
    }
}
