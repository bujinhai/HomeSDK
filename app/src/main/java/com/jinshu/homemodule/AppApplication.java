package com.jinshu.homemodule;


import android.app.Application;

import com.jinshu.homelibrary.baseapp.FBaseSdk;
import com.jinshu.homelibrary.entity.FBuilder;

/**
 * Create on 2019/9/12 09:41 by bll
 */


public class AppApplication extends Application {

    private String siteID = "8a2f462a6aa17470016aa47bac8f0f32";
    private String applicationID = "8a2f462a6aa17470016aa47baa1c0f24";

    @Override
    public void onCreate() {
        super.onCreate();
        //HomeSDK
        FBaseSdk.init(new FBuilder(this).setSiteID(siteID).
                setApplicationID(applicationID).build());
    }
}
