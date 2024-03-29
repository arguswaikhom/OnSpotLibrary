package com.crown.library.onspotlibrary.model;

import androidx.annotation.NonNull;

import com.crown.library.onspotlibrary.utils.ListItemType;
import com.google.gson.Gson;

public class DeliveryPartnerOSB extends ListItem {
    private String status;
    private String userId;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public int getItemType() {
        return ListItemType.DELIVERY_PARTNER_OSB;
    }

    @NonNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}