package com.crown.library.onspotlibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.crown.library.onspotlibrary.controller.OSPreferences;
import com.crown.library.onspotlibrary.model.user.UserV0;
import com.crown.library.onspotlibrary.utils.emun.OSPreferenceKey;

import static android.content.Context.LOCATION_SERVICE;

public class OSConfig {
    public static final int DEFAULT_RES = -111;

    /**
     * Check if the device is connected to internet
     *
     * @return true: if connected, false: if not connected, null: if failed to complete the method execution
     */
    public static Boolean hasNetworkConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) return null;
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    /**
     * Check id the device GPS is enabled
     *
     * @return true: if enabled, false: if not enable, null: if failed to complete the method execution
     */
    public static Boolean isGPSEnabled(Context context) {
        LocationManager lm = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        if (lm == null) return null;
        boolean gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        return gps_enabled && network_enabled;
    }

    /**
     * Open app settings
     */
    public static void appSettings(Activity activity) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
        intent.setData(uri);
        activity.startActivityForResult(intent, 101);
    }

    /**
     * Request system to open soft keyboard
     *
     * @param editText: The editText which require the keyboard input
     */
    public static void requestKeyboard(View editText) {
        editText.post(() -> {
            editText.requestFocusFromTouch();
            InputMethodManager lManager = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            if (lManager != null) lManager.showSoftInput(editText, 0);
        });
    }

    /**
     * Check if the shared preferences has user's info
     *
     * @param context: Content to get shared preferences
     * @return: true if shared preferences has user's info otherwise false
     */
    public static boolean isAuthenticated(Context context) {
        UserV0 user = OSPreferences.getInstance(context).getObject(OSPreferenceKey.USER, UserV0.class);
        return user != null && !TextUtils.isEmpty(user.getUserId());
    }
}
