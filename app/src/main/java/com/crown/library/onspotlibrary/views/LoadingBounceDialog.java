package com.crown.library.onspotlibrary.views;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.crown.library.onspotlibrary.R;

public class LoadingBounceDialog {

    private final Activity activity;
    private final AlertDialog dialog;

    public LoadingBounceDialog(Activity activity) {
        this.activity = activity;
        View dialogView = LayoutInflater.from(activity).inflate(R.layout.dialog_dots_bounce_loading, null);
        dialog = new AlertDialog.Builder(activity, R.style.ThemeDialog).setView(dialogView).setCancelable(false).create();
    }

    public void show() {
        if (activity != null && !activity.isFinishing()) dialog.show();
    }

    public void dismiss() {
        if (dialog.isShowing()) dialog.dismiss();
    }

    public boolean isShowing() {
        return dialog.isShowing();
    }
}
