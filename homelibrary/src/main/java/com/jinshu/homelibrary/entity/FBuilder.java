package com.jinshu.homelibrary.entity;

import android.content.Context;

import com.jinshu.homelibrary.baseapp.FAppConstant;
import com.jinshu.homelibrary.baseapp.FBaseSdk;
import com.jinshu.homelibrary.utils.SPUtils;


/**
 * Create on 2019/9/23 11:11 by bll
 */


public class FBuilder {

    public FBuilder(Context context) {
        FBaseSdk.setAppContext(context);
    }

    public FBuilder setSiteID(String siteID) {
        SPUtils.remove(FAppConstant.SITE_ID);
        SPUtils.setSharedStringData(FAppConstant.SITE_ID, siteID);
        return this;
    }

    public FBuilder setApplicationID(String applicationID) {
        SPUtils.remove(FAppConstant.APPLICATION_ID);
        SPUtils.setSharedStringData(FAppConstant.APPLICATION_ID, applicationID);
        return this;
    }

    public FBuilder build() {
        return this;
    }
}
