package com.crown.library.onspotlibrary.utils.callback;

import com.google.firebase.firestore.DocumentSnapshot;

public interface OSFirebaseDocResponse {
    void onResponse(DocumentSnapshot doc, Exception e);
}
