package com.jinshu.homelibrary.base;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.jinshu.homelibrary.baseapp.FAppManager;
import com.jinshu.homelibrary.baserx.FRxManager;
import com.jinshu.homelibrary.utils.ToastUtil;


/**
 * 基类
 */

public abstract class FBaseActivity extends AppCompatActivity {

    protected Context mContext;
    protected Activity mActivity;
    protected FRxManager mRxManager;
    private boolean isConfigChange = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isConfigChange = false;
        mRxManager = new FRxManager();
        doBeforeSetcontentView();
        setContentView(getLayoutId());
        mContext = this;
        mActivity = this;
        this.initView(getIntent(), savedInstanceState);
        this.onInitData(getIntent(), savedInstanceState);
    }

    /**
     * 设置layout前配置
     */
    private void doBeforeSetcontentView() {
        // 把actvity放到application栈中管理
        FAppManager.getAppManager().addActivity(this);
        // 无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // 设置沉浸式状态栏
//        setTranslanteBar();
    }

    /**
     * 获取布局
     */
    public abstract int getLayoutId();

    /**
     * 初始化view
     */
    public abstract void initView(Intent intent, @Nullable Bundle savedInstanceState);

    /**
     * 请求数据
     */
    protected void onInitData(Intent intent, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        isConfigChange = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mRxManager != null) {
            mRxManager.clear();
        }
        if (!isConfigChange) {
            FAppManager.getAppManager().finishActivity(this);
        }
        ToastUtil.cancelToast();
    }
}
