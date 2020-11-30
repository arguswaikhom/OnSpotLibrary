package com.crown.library.onspotlibrary.model.order;

import androidx.annotation.NonNull;

import com.crown.library.onspotlibrary.utils.ListItemType;
import com.google.gson.Gson;

/**
 * *This class is use only to differentiate ViewHolders
 * *for my delivery list in OSD
 */
public class OSMyDeliveryOrderOSD extends OSOrder {

    public OSMyDeliveryOrderOSD() {
    }

    @Override
    public int getItemType() {
        return ListItemType.OS_MY_DELIVERY_ORDER_OSD;
    }

    @NonNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
