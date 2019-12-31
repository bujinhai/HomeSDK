package com.jinshu.homemodule;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;

import com.jinshu.homelibrary.api.FApi;
import com.jinshu.homelibrary.api.FHostType;
import com.jinshu.homelibrary.base.FBaseActivity;
import com.jinshu.homelibrary.baseapp.FAppConstant;
import com.jinshu.homelibrary.baseapp.FBaseSdk;
import com.jinshu.homelibrary.baserx.FRxHelper;
import com.jinshu.homelibrary.baserx.FRxSchedulers;
import com.jinshu.homelibrary.baserx.FRxSubscriber;
import com.jinshu.homelibrary.entity.FUserEntity;
import com.jinshu.homelibrary.utils.BaseUtils;
import com.jinshu.homelibrary.utils.JumpUtils;
import com.jinshu.homelibrary.utils.SPUtils;
import com.jinshu.homelibrary.utils.StrUtils;
import com.jinshu.homelibrary.utils.ToastUtil;

/**
 * Create on 2019/11/18 09:32 by bll
 */


public class LoginActivity extends FBaseActivity {

    private EditText etLoginName;
    private EditText etPsw;
    private Button btnLogin;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView(Intent intent, @Nullable Bundle savedInstanceState) {
        etLoginName = findViewById(R.id.et_user_name);
        etPsw = findViewById(R.id.et_user_psw);
        btnLogin = findViewById(R.id.btn_login);

        String userName = SPUtils.getSharedStringData(FAppConstant.KEY_USER);
        String psw = SPUtils.getSharedStringData(FAppConstant.KEY_PASSWORD);
        if (StrUtils.isNotEmpty(userName) && StrUtils.isNotEmpty(psw)) {
            etLoginName.setText(userName);
            etPsw.setText(psw);
        }

        btnLogin.setOnClickListener(v -> {

            login();
        });
    }

    private void login() {
        String name = etLoginName.getText().toString();
        String psw = etPsw.getText().toString();

        FApi.getDefault(FHostType.BASE_URL)
                .login(name, psw, BaseUtils.addLoginInfo())
                .compose(FRxSchedulers.io_main())
                .compose(FRxHelper.handleResult())
                .subscribe(new FRxSubscriber<FUserEntity>(this, "登录中...", true) {

                    @Override
                    protected void _onNext(FUserEntity entity) {
                        //首页sdk
                        FBaseSdk.setSessionID(entity.getSessionID());
                        FBaseSdk.setMemberID(entity.getMemberID());

                        SPUtils.setSharedStringData(FAppConstant.KEY_USER, name);
                        SPUtils.setSharedStringData(FAppConstant.KEY_PASSWORD, psw);
                        JumpUtils.jumpActivity(LoginActivity.this, MainActivity.class);
                        finish();
                    }

                    @Override
                    protected void _onError(String message) {
                        ToastUtil.showShort(message);
                    }
                });
    }
}
