package com.jinshu.homelibrary.utils;


import com.jinshu.homelibrary.baseapp.FAppConstant;

import java.util.HashMap;

public class BaseUtils {


    public static String getSiteID() {
        String siteID = SPUtils.getSharedStringData(FAppConstant.SITE_ID);
        if (siteID != null) {
            return siteID;
        }
        return null;
    }

    public static String getApplicationID() {
        String applicationID = SPUtils.getSharedStringData(FAppConstant.APPLICATION_ID);
        if (applicationID != null) {
            return applicationID;
        }
        return null;
    }


    public static String getSessionID() {
        String sessionID = SPUtils.getSharedStringData(FAppConstant.SESSION_ID);
        if (sessionID != null) {
            return sessionID;
        }
        return null;
    }

    public static String getMemberID() {
        String memberID = SPUtils.getSharedStringData(FAppConstant.MEMBER_ID);
        if (memberID != null) {
            return memberID;
        }
        return null;
    }

    public static HashMap<String, String> addLoginInfo() {

        HashMap<String, String> params = new HashMap<>();

//        String deviceID = PreferenceHelper.getString(SApiConstants.DEVICE_ID);
//        if (!TextUtils.isEmpty(deviceID)) {
//            params.put("deviceID", deviceID);
//        }
        params.put("deviceID", "8a2f462a60de32290160de81d31f0141");
        String siteID = getSiteID();
        if (siteID != null) {
            params.put("siteID", siteID);
        }

        return params;
    }
}
