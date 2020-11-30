package com.crown.library.onspotlibrary.model.order;

import androidx.annotation.NonNull;

import com.crown.library.onspotlibrary.utils.ListItemType;
import com.google.gson.Gson;

/**
 * *This class is use only to differentiate ViewHolders
 * *for orders to details destination path
 */
public class OSOrderPathDetails extends OSOrder {

    public OSOrderPathDetails() {
    }

    @Override
    public int getItemType() {
        return ListItemType.OS_ORDER_PATH_DETAILS;
    }

    @NonNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
