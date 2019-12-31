package com.jinshu.homelibrary.api;

/**
 * Create on 2019/9/10 15:11 by bll
 */


public class FApiConstants {

    /**
     * 服务器地址
     */
    public static final String BaseUrl = "https://www.haoju.me/interface-server/api/";

    /**
     * 获取对应的host
     *
     * @param hostType host类型
     * @return host
     */
    protected static String getHost(int hostType) {
        String host;
        switch (hostType) {
            case FHostType.BASE_URL:
                host = BaseUrl;
                break;
            default:
                host = "";
                break;
        }
        return host;
    }
}
