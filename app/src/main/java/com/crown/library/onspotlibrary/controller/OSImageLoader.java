package com.crown.library.onspotlibrary.controller;

import android.content.Context;

import com.crown.library.onspotlibrary.R;
import com.crown.library.onspotlibrary.model.businessItem.BusinessItemV0;
import com.crown.library.onspotlibrary.utils.OSVolleyCacheUtils;
import com.crown.library.onspotlibrary.utils.callback.OnFailResponse;
import com.crown.library.onspotlibrary.utils.callback.OnStringResponse;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class OSImageLoader {
    @Deprecated
    private static final String REF_USER_PROFILE = "user-profile";
    private static final String REF_BUSINESS_PROFILE = "business-profile";

    @Deprecated
    public static void getUserProfileImage(Context context, String uid, OnReturnImageUrl success, OnFailed failed) {
        OSVolley osVolley = OSVolley.getInstance(context);
        String url = osVolley.getCache(uid);
        if (url != null) {
            success.onReturnImageUrl(url);
            return;
        }
        FirebaseStorage.getInstance().getReference().child(REF_USER_PROFILE).child(uid).getDownloadUrl().addOnSuccessListener(uri -> {
            osVolley.setCache(uid, uri.toString(), null);
            success.onReturnImageUrl(uri.toString());
        }).addOnFailureListener(e -> {
            if (failed != null) failed.onFailed(e);
        });
    }

    public static void getUserProfileImage(Context context, String uid, OnStringResponse success, OnFailResponse failed) {
        OSVolley osVolley = OSVolley.getInstance(context);
        String cacheKey = context.getString(R.string.pre_field_image) + uid;
        String url = osVolley.getCache(cacheKey);
        if (url != null) {
            success.onResponse(url);
            return;
        }
        FirebaseStorage.getInstance().getReference().child(REF_USER_PROFILE).child(uid).getDownloadUrl().addOnSuccessListener(uri -> {
            osVolley.setCache(cacheKey, uri.toString(), null);
            success.onResponse(uri.toString());
        }).addOnFailureListener(e -> {
            if (failed != null) failed.onFailed(e, e.getMessage());
        });
    }

    public static void getBusinessItemImage(Context context, BusinessItemV0 itemV0, OnStringResponse success) {
        String imageUrl = OSVolleyCacheUtils.getBusinessItemImage(context, itemV0);
        if (imageUrl != null) {
            success.onResponse(imageUrl);
            return;
        }
        FirebaseStorage.getInstance().getReference().child(context.getString(R.string.sref_item_image)).child(itemV0.getBusinessRefId()).child(itemV0.getItemId()).listAll().addOnSuccessListener(listResult -> {
            List<StorageReference> referenceList = listResult.getItems();
            if (referenceList.size() > 0) {
                referenceList.get(0).getDownloadUrl().addOnSuccessListener(result -> {
                    if (result == null) return;
                    OSVolleyCacheUtils.setBusinessItemImage(context, itemV0.getItemId(), result.toString());
                    success.onResponse(result.toString());
                });
            }
        });
    }

    @Deprecated
    public interface OnReturnImageUrl {
        @Deprecated
        void onReturnImageUrl(String url);
    }

    @Deprecated
    public interface OnFailed {
        @Deprecated
        void onFailed(Exception e);
    }
}
