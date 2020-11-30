package com.crown.library.onspotlibrary.utils;

import android.content.Context;

import com.crown.library.onspotlibrary.controller.OSVolley;
import com.crown.library.onspotlibrary.model.businessItem.BusinessItemV0;
import com.crown.library.onspotlibrary.model.businessItem.BusinessItemV4;
import com.google.gson.Gson;

public class OSVolleyCacheUtils {

    public static String getBusinessItemImage(Context context, BusinessItemV0 itemV0) {
        return getBusinessItemImage(context, itemV0.getItemId());
    }

    public static String getBusinessItemImage(Context context, String itemId) {
        OSVolley osVolley = OSVolley.getInstance(context);
        String imageUrl = osVolley.getCache(OSString.preFieldImage + itemId);
        if (imageUrl != null) return imageUrl;
        String imageUrls = osVolley.getCache(OSString.preFieldImages + itemId);
        if (imageUrls != null) return new Gson().fromJson(imageUrls, String[].class)[0];
        return null;
    }

    public static void setBusinessItemImage(Context context, String itemId, String imageUrl) {
        OSVolley osVolley = OSVolley.getInstance(context);
        osVolley.setCache(OSString.preFieldImage + itemId, imageUrl);
    }

    public static <T extends BusinessItemV4> void setBusinessItemImages(Context context, T itemV0) {
        if (itemV0 == null || itemV0.getImageUrls() == null || itemV0.getImageUrls().isEmpty()) {
            return;
        }
        OSVolley osVolley = OSVolley.getInstance(context);
        osVolley.setCache(OSString.preFieldImages + itemV0.getItemId(), new Gson().toJson(itemV0.getImageUrls()));
    }
}
