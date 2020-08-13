package com.crown.library.onspotlibrary.utils.callback;

import android.view.View;

public interface OSCartQuantityViewClickListener {
    int ADD = 1;
    int SUB = 2;

    void onQuantityChange(View view, int mode, int position);
}