package com.crown.library.onspotlibrary.controller;

import android.content.Context;

import com.google.firebase.storage.FirebaseStorage;

public class OSImageLoader {
    private static final String REF_USER_PROFILE = "user-profile";
    private static final String REF_BUSINESS_PROFILE = "business-profile";

    public interface OnReturnImageUrl {
        void onReturnImageUrl(String url);
    }

    public interface OnFailed {
        void onFailed(Exception e);
    }

    public static void getUserProfileImage(Context context, String uid, OnReturnImageUrl success, OnFailed failed) {
        OSVolley osVolley = OSVolley.getInstance(context);
        String url = osVolley.getCache(uid);
        if (url != null)
            success.onReturnImageUrl(url);
        FirebaseStorage.getInstance().getReference().child(REF_USER_PROFILE).child(uid).getDownloadUrl().addOnSuccessListener(uri -> {
            osVolley.setCache(uid, uri.toString(), null);
            success.onReturnImageUrl(uri.toString());
        }).addOnFailureListener(e -> {
            if (failed != null) failed.onFailed(e);
        });
    }
}
