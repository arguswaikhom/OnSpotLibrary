package com.crown.library.onspotlibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class OSMessage {

    /**
     * Indefinite, Action Snack Bar
     */
    public static void showAIBar(Activity activity, String msg, String action, View.OnClickListener onAction) {
        View v = activity.findViewById(android.R.id.content);
        Snackbar.make(v, msg, Snackbar.LENGTH_INDEFINITE).setAction(action, onAction).show();
    }

    public static void showSBar(Activity activity, String msg) {
        View v = activity.findViewById(android.R.id.content);
        Snackbar.make(v, msg, Snackbar.LENGTH_SHORT).show();
    }

    public static void showLBar(Activity activity, String msg) {
        View v = activity.findViewById(android.R.id.content);
        Snackbar.make(v, msg, Snackbar.LENGTH_LONG).show();
    }

    public static void showSToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void showLToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
