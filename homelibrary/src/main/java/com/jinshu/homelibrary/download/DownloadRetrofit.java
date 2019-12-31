package com.jinshu.homelibrary.download;


import com.jinshu.homelibrary.api.FApiConstants;
import com.jinshu.homelibrary.api.FApiService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Create on 2019/8/29 09:56 by bll
 * 为下载单独建一个retrofit
 */


public class DownloadRetrofit {

    private static DownloadRetrofit instance;
    private Retrofit mRetrofit;
    public FApiService apiService;

    public DownloadRetrofit() {

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new FileIntercepter())
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(FApiConstants.BaseUrl)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        apiService = mRetrofit.create(FApiService.class);

    }


    public static DownloadRetrofit getInstance() {

        if (instance == null) {
            synchronized (DownloadRetrofit.class) {
                if (instance == null) {
                    instance = new DownloadRetrofit();
                }
            }

        }
        return instance;
    }

    public FApiService getApiService() {
        return apiService;
    }

}
