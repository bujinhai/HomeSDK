package com.jinshu.homelibrary.baserx;


import com.jinshu.homelibrary.basebean.FBaseResponse;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * des:对服务器返回数据成功和失败处理
 * Created by xsf
 * on 2016.09.9:59
 */

/**************使用例子******************/
/*_apiService.login(mobile, verifyCode)
        .compose(RxSchedulersHelper.io_main())
        .compose(RxResultHelper.handleResult())
        .//省略*/

public class FRxHelper {
    /**
     * 对服务器返回数据进行预处理
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<FBaseResponse<T>, T> handleResult() {
        return new ObservableTransformer<FBaseResponse<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<FBaseResponse<T>> upstream) {
                return upstream.flatMap(new Function<FBaseResponse<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(FBaseResponse<T> result) throws Exception {
                        if (result.success()) {
                            return createData(result.body);
                        } else if (result.faild()) {
                            return null;
                        } else {
                            return Observable.error(new RuntimeException(result.header.msg));
                        }
                    }
                }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };

    }

    /**
     * 创建成功的数据
     *
     * @param data
     * @param <T>
     * @return
     */
    private static <T> Observable<T> createData(final T data) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> subscriber) throws Exception {
                try {
                    subscriber.onNext(data);
                    subscriber.onComplete();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });

    }
}
