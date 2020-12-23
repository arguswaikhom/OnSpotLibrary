package com.crown.library.onspotlibrary.controller;

import android.content.Context;
import android.content.SharedPreferences;

import com.crown.library.onspotlibrary.utils.emun.OSPreferenceKey;
import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;

public class OSPreferences {
    private static final OSPreferences instance = null;
    private final SharedPreferences preferences;

    private OSPreferences(Context context) {
        this.preferences = context.getSharedPreferences(OSPreferenceKey.PRIMARY_SP.name(), MODE_PRIVATE);
    }

    public static OSPreferences getInstance(Context context) {
        if (instance == null) return new OSPreferences(context);
        return instance;
    }

    public void setObject(Object object, OSPreferenceKey key) {
        String value;
        if (object.getClass().equals(String.class)) value = (String) object;
        else value = new Gson().toJson(object);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key.name(), value);
        editor.apply();
    }

    @SuppressWarnings("unchecked")
    public <T> T getObject(OSPreferenceKey key, Class<T> tClass) {
        String json = preferences.getString(key.name(), null);
        if (json != null) {
            try {
                if (tClass.equals(String.class)) return (T) json;
                return new Gson().fromJson(json, tClass);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public void clearAll() {
        preferences.edit().clear().apply();
    }
}
