package com.crown.library.onspotlibrary.model.business;

import androidx.annotation.NonNull;

import com.crown.library.onspotlibrary.utils.ListItemType;
import com.google.gson.Gson;

import java.util.List;

public class BusinessV6 extends BusinessV5 {
    private List<String> deviceToken;

    @Override
    public int getItemType() {
        return ListItemType.BUSINESS_V6;
    }

    public List<String> getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(List<String> deviceToken) {
        this.deviceToken = deviceToken;
    }

    @NonNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
