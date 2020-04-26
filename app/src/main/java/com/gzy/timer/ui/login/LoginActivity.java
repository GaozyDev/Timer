package com.gzy.timer.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;

import com.gzy.timer.Constant;
import com.gzy.timer.R;
import com.gzy.timer.ui.BaseActivity;
import com.gzy.timer.ui.main.MainActivity;
import com.gzy.timer.utils.SpUtils;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

/**
 * 登录页
 */
public class LoginActivity extends BaseActivity {

    private Button mLoginBtn;

    private ProgressBar mLoadingPb;

    private LoginViewModel mLoginViewModel;

    private Tencent mTencent;

    private String mOpenId;

    private String mAccessToken;

    private String mExpiresIn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mTencent = Tencent.createInstance(Constant.App.QQ_APP_ID, this.getApplicationContext());

        initView();
        initViewModel();
        initViewClickListener();
    }

    private void initView() {
        mLoginBtn = findViewById(R.id.login_btn);
        mLoadingPb = findViewById(R.id.login_loading_pb);
    }

    private void initViewModel() {
        mLoginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        mLoginViewModel.getLoginResult().observe(this, loginResult -> {
            boolean success = loginResult.getSuccess() != null;
            setLoginStatus(success);
            if (success) {
                loginSuccess(loginResult.getSuccess());
            } else {
                mTencent.logout(LoginActivity.this);
                Toast.makeText(LoginActivity.this, loginResult.getError(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loginSuccess(String token) {
        // 保存登录信息
        SpUtils.putString(LoginActivity.this, Constant.Sp.QQ_OPEN_ID, mOpenId);
        SpUtils.putString(LoginActivity.this, Constant.Sp.QQ_ACCESS_TOKEN, mAccessToken);
        SpUtils.putString(LoginActivity.this, Constant.Sp.QQ_EXPIRES_IN, mExpiresIn);
        SpUtils.putString(LoginActivity.this, Constant.Sp.TOKEN, token);

        // 跳转主界面
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    private void initViewClickListener() {
        mLoginBtn.setOnClickListener(v -> {
            setLoginStatus(true);
            mTencent.login(LoginActivity.this, "all", mIUiListener);
        });
    }

    private IUiListener mIUiListener = new IUiListener() {

        @Override
        public void onComplete(Object object) {
            if (object == null) {
                return;
            }
            JSONObject jsonObject = (JSONObject) object;
            if (jsonObject.length() == 0) {
                return;
            }

            try {
                mOpenId = jsonObject.getString(Constants.PARAM_OPEN_ID);
                mAccessToken = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
                mExpiresIn = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
                if (!TextUtils.isEmpty(mOpenId) && !TextUtils.isEmpty(mExpiresIn)
                        && !TextUtils.isEmpty(mAccessToken)) {
                    mLoginViewModel.login(mOpenId, mAccessToken);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(UiError uiError) {
            setLoginStatus(false);
        }

        @Override
        public void onCancel() {
            setLoginStatus(false);
        }
    };

    /**
     * 当前是否正在登录状态
     *
     * @param login true 正在登录
     */
    private void setLoginStatus(boolean login) {
        mLoginBtn.setVisibility(login ? View.GONE : View.VISIBLE);
        mLoadingPb.setVisibility(login ? View.VISIBLE : View.GONE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constants.REQUEST_LOGIN) {
            Tencent.onActivityResultData(requestCode, resultCode, data, mIUiListener);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
