package com.crown.library.onspotlibrary.utils;

import com.crown.library.onspotlibrary.utils.callback.OSFirebaseDocResponse;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

public class OSFirebaseDocUtils {
    public static void getBusiness(@NotNull String id, @NotNull OSFirebaseDocResponse response) {
        FirebaseFirestore.getInstance().collection(OSString.refBusiness).document(id).get()
                .addOnSuccessListener(doc -> response.onResponse(doc, null))
                .addOnFailureListener(e -> response.onResponse(null, e));
    }

    public static void getUser(@NotNull String id, @NotNull OSFirebaseDocResponse response) {
        FirebaseFirestore.getInstance().collection(OSString.refUser).document(id).get()
                .addOnSuccessListener(doc -> response.onResponse(doc, null))
                .addOnFailureListener(e -> response.onResponse(null, e));
    }
}
