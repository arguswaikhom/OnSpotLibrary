package com.crown.library.onspotlibrary.utils.callback;

import android.net.Uri;

public interface OnImageDownload {
    void onImageDownloadSuccessful(Uri uri);

    void onImageDownloadFailure();
}
