package com.crown.library.onspotlibrary.utils;

import android.content.Context;

import com.crown.library.onspotlibrary.R;
import com.crown.library.onspotlibrary.utils.callback.OnBooleanResponse;
import com.crown.library.onspotlibrary.utils.callback.OnFailResponse;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

@Deprecated
public class OSLaunchUtil {

    @SuppressWarnings("unchecked")
    public static void isAvailable(Context context, String postalCode, OnBooleanResponse response, OnFailResponse failResponse) {
        FirebaseFirestore.getInstance().collection("crown-onspot")
                .document("launchRegion").get()
                .addOnSuccessListener(documentSnapshot -> {
                    try {
                        List<String> postalCodes = (List<String>) documentSnapshot.get("postalCode");
                        assert postalCodes != null;
                        response.onResponse(postalCodes.contains(postalCode.trim()));
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (failResponse != null) failResponse.onFailed(e, e.getMessage());
                    }
                })
                .addOnFailureListener(e -> {
                    if (failResponse != null) failResponse.onFailed(e, e.getMessage());
                });
    }

}
