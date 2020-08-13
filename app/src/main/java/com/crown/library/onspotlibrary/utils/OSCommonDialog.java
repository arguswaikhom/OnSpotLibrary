package com.crown.library.onspotlibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import androidx.appcompat.app.AlertDialog;

import com.crown.library.onspotlibrary.R;

public class OSCommonDialog {
    public static void requireGPS(Activity activity) {
        String message = "Your location information is require to use OnSpot Business";
        new AlertDialog.Builder(activity)
                .setTitle(activity.getString(R.string.app_name))
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Enable", (paramDialogInterface, paramInt) -> activity.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)))
                .setNegativeButton("Cancel", (dialog, which) -> dialog.cancel())
                .show();
    }

    public static void appSettings(Activity activity) {
        new AlertDialog.Builder(activity)
                .setTitle("Grant Permissions")
                .setMessage("This app needs permission to use this feature. You can grant them in app settings.")
                .setPositiveButton("Goto Settings", (dialog, which) -> {
                    dialog.cancel();
                    OSConfig.appSettings(activity);
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.cancel())
                .show();
    }

    public static void appUnavailable(Context context, String name, DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(context)
                .setTitle(name)
                .setCancelable(false)
                .setMessage(name + " is not available in your current location. You cannot use the app unless you have an existing account.")
                .setPositiveButton("Sign in", listener)
                .setNegativeButton("Cancel", (dialog, which) -> dialog.cancel())
                .show();
    }

    public static void locationError(Activity activity) {
        new AlertDialog.Builder(activity)
                .setTitle("Location error")
                .setMessage("Unable to get your current location. Make sure you turn on location. If that doesn't work, open Google Maps app, load your current location and try again.")
                .setPositiveButton("Open Map", ((dialog, which) -> {
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/"));
                    activity.startActivity(intent);
                }))
                .setNegativeButton("Cancel", (dialog, which) -> dialog.cancel())
                .show();
    }
}
