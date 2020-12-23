package com.crown.library.onspotlibrary.utils;

public class OSInAppUrlUtils {
    public static String getProductUrl(String id) {
        return OSString.linkProduct + "/" + id;
    }

    public static String getBusinessUrl(String id) {
        return OSString.linkBusiness + "/" + id;
    }

    public static String getBusinessOrderOnlineUrl(String id) {
        return OSString.linkOrderOnline + "/" + id;
    }
}
