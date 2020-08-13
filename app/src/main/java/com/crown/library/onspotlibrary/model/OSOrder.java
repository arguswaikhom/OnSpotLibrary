package com.crown.library.onspotlibrary.model;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

// *os done
@Deprecated
public class OSOrder extends OSOrderUpload {
    private String orderId;

    public OSOrder() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @NonNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
