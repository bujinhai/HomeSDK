package com.jinshu.homelibrary.baseapp;

import android.content.Context;

import com.jinshu.homelibrary.entity.FBuilder;
import com.jinshu.homelibrary.utils.SPUtils;

/**
 * Create on 2019/9/30 14:25 by bll
 */


public class FBaseSdk {

    private static Context mContext;

    public static void init(FBuilder builder) {
        if (builder == null) {
            throw new IllegalArgumentException("Bankcard builder can not be initialized with null.");
        }
    }

    public static void setAppContext(Context context) {
        mContext = context;
    }

    public static Context getAppContext() {
        return mContext;
    }

    public static void setSessionID(String sessionID) {
        SPUtils.remove(FAppConstant.SESSION_ID);
        SPUtils.setSharedStringData(FAppConstant.SESSION_ID, sessionID);
    }

    public static void setMemberID(String memberID) {
        SPUtils.remove(FAppConstant.MEMBER_ID);
        SPUtils.setSharedStringData(FAppConstant.MEMBER_ID, memberID);
    }
}
