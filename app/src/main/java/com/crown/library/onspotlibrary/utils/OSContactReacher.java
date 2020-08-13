package com.crown.library.onspotlibrary.utils;

import android.content.Context;

import com.crown.library.onspotlibrary.R;
import com.crown.library.onspotlibrary.utils.callback.OnFailResponse;
import com.crown.library.onspotlibrary.utils.callback.OnStringResponse;
import com.crown.library.onspotlibrary.utils.callback.OnSuccessResponse;
import com.google.firebase.firestore.FirebaseFirestore;

public class OSContactReacher {
    // *os done
    @Deprecated
    public static void getUserMobileNumber(Context context, String uid, OnSuccessResponse success, OnFailResponse failure) {
        FirebaseFirestore.getInstance().collection(context.getString(R.string.ref_user)).document(uid).get().addOnSuccessListener(doc -> {
            String phoneNo = (String) doc.get(context.getString(R.string.field_phone_number));
            if (phoneNo != null) success.onSuccess(phoneNo);
            else if (failure != null) failure.onFailed(null, "Contact not found");
        }).addOnFailureListener(e -> {
            if (failure != null) failure.onFailed(e, "Failed to get contact");
        });
    }

    public static void getUserMobileNumber(Context context, String uid, OnStringResponse success, OnFailResponse failure) {
        FirebaseFirestore.getInstance().collection(context.getString(R.string.ref_user)).document(uid).get().addOnSuccessListener(doc -> {
            String phoneNo = (String) doc.get(context.getString(R.string.field_phone_number));
            if (phoneNo != null) success.onResponse(phoneNo);
            else if (failure != null) failure.onFailed(null, "Contact not found");
        }).addOnFailureListener(e -> {
            if (failure != null) failure.onFailed(e, "Failed to get contact");
        });
    }

    public static void getBusinessMobileNumber(Context context, String bussRefId, OnStringResponse success, OnFailResponse failure) {
        FirebaseFirestore.getInstance().collection(context.getString(R.string.ref_business)).document(bussRefId).get().addOnSuccessListener(doc -> {
            String phoneNo = (String) doc.get(context.getString(R.string.field_mobile_number));
            if (phoneNo != null) success.onResponse(phoneNo);
            else if (failure != null) failure.onFailed(null, "Contact not found");
        }).addOnFailureListener(e -> {
            if (failure != null) failure.onFailed(e, "Failed to get contact");
        });
    }
}
