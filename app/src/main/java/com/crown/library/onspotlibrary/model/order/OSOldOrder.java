package com.crown.library.onspotlibrary.model.order;

import androidx.annotation.NonNull;

import com.crown.library.onspotlibrary.utils.ListItemType;
import com.google.gson.Gson;

public class OSOldOrder extends OSOrder {

    public OSOldOrder() {
    }

    @Override
    public int getItemType() {
        return ListItemType.OS_OLD_ORDER;
    }

    @NonNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
