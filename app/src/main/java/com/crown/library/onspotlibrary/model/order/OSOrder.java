package com.crown.library.onspotlibrary.model.order;

import androidx.annotation.NonNull;

import com.crown.library.onspotlibrary.utils.ListItemType;
import com.google.gson.Gson;

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

    @Override
    public int getItemType() {
        return ListItemType.OS_ORDER;
    }

    @NonNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
