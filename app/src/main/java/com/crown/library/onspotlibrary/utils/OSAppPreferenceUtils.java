package com.crown.library.onspotlibrary.utils;

import android.content.Context;

import com.crown.library.onspotlibrary.controller.OSPreferences;
import com.crown.library.onspotlibrary.model.preference.OSBusinessAppPreferences;
import com.crown.library.onspotlibrary.model.preference.OSDeliveryAppPreferences;
import com.crown.library.onspotlibrary.model.preference.OSOnSpotAppPreferences;
import com.crown.library.onspotlibrary.utils.emun.OSPreferenceKey;

public class OSAppPreferenceUtils {
    public static OSOnSpotAppPreferences getOSPreferences(Context context) {
        OSOnSpotAppPreferences preferences = OSPreferences.getInstance(context).getObject(OSPreferenceKey.APP_PREFERENCES, OSOnSpotAppPreferences.class);
        if (preferences != null) return preferences;
        return new OSOnSpotAppPreferences();
    }

    public static OSBusinessAppPreferences getOSBPreferences(Context context) {
        OSBusinessAppPreferences preferences = OSPreferences.getInstance(context).getObject(OSPreferenceKey.APP_PREFERENCES, OSBusinessAppPreferences.class);
        if (preferences != null) return preferences;
        return new OSBusinessAppPreferences();
    }

    public static OSDeliveryAppPreferences getOSDPreferences(Context context) {
        OSDeliveryAppPreferences preferences = OSPreferences.getInstance(context).getObject(OSPreferenceKey.APP_PREFERENCES, OSDeliveryAppPreferences.class);
        if (preferences != null) return preferences;
        return new OSDeliveryAppPreferences();
    }
}
