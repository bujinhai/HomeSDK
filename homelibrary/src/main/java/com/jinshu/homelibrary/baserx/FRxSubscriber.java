package com.jinshu.homelibrary.baserx;

import android.app.Activity;
import android.content.Context;

import com.jinshu.homelibrary.R;
import com.jinshu.homelibrary.baseapp.FBaseSdk;
import com.jinshu.homelibrary.utils.FNetUtils;
import com.jinshu.homelibrary.widget.FLoadingDialog;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;


/**
 * des:订阅封装
 * Created by xsf
 * on 2016.09.10:16
 */

/********************使用例子********************/
/*_apiService.login(mobile, verifyCode)
        .//省略
        .subscribe(new FRxSubscriber<User user>(mContext,false) {
@Override
public void _onNext(User user) {
        // 处理user
        }

@Override
public void _onError(String msg) {
        ToastUtil.showShort(mActivity, msg);
        });*/
public abstract class FRxSubscriber<T> implements Observer<T> {

    private Context mContext;
    private String msg;
    private boolean showDialog = true;

    /**
     * 是否显示浮动dialog
     */
    public void showDialog() {
        this.showDialog = true;
    }

    public void hideDialog() {
        this.showDialog = true;
    }

    public FRxSubscriber(Context context, String msg, boolean showDialog) {
        this.mContext = context;
        this.msg = msg;
        this.showDialog = showDialog;
    }

    public FRxSubscriber(Context context) {
        this(context, FBaseSdk.getAppContext().getString(R.string.library_loading), true);
    }

    public FRxSubscriber(Context context, boolean showDialog) {
        this(context, FBaseSdk.getAppContext().getString(R.string.library_loading), showDialog);
    }

    @Override
    public void onComplete() {
        if (showDialog)
            FLoadingDialog.cancelDialogForLoading();
    }

    @Override
    public void onSubscribe(Disposable d) {
        if (showDialog) {
            try {
                FLoadingDialog.showDialogForLoading((Activity) mContext, msg, true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
        if (showDialog)
            FLoadingDialog.cancelDialogForLoading();
    }

    @Override
    public void onError(Throwable e) {
        if (showDialog) {
            FLoadingDialog.cancelDialogForLoading();
        }
        e.printStackTrace();
        if (!FNetUtils.isNetConnected(FBaseSdk.getAppContext())) {
            //网络
            _onError(FBaseSdk.getAppContext().getString(R.string.library_no_net));
        } else if (e instanceof HttpException) {
            _onError("服务器异常 404");
        } else if (e.toString().contains("The mapper function returned a null value")) {
            _onError(FBaseSdk.getAppContext().getString(R.string.library_no_data));
        } else {
            //其它
            _onError(FBaseSdk.getAppContext().getString(R.string.library_net_error));
        }
    }

    protected abstract void _onNext(T t);

    protected abstract void _onError(String message);

}
