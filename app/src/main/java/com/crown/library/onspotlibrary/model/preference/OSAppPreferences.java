package com.crown.library.onspotlibrary.model.preference;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

public class OSAppPreferences {

    public static OSAppPreferences fromJson(String json) {
        return new Gson().fromJson(json, OSAppPreferences.class);
    }

    @NonNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
