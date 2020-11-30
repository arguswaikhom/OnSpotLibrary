package com.crown.library.onspotlibrary.utils;

public class OSInAppUrlUtils {
    public static String getProductUrl(String id) {
        return "http://onspotpackage.com/product/" + id;
    }

    public static String getBusinessUrl(String id) {
        return "http://onspotpackage.com/business/" + id;
    }

    public static String getBusinessOrderOnlineUrl(String id) {
        return "http://onspotpackage.com/order-online/" + id;
    }
}
