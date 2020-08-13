package com.crown.library.onspotlibrary.model.businessItem;

import com.crown.library.onspotlibrary.model.OSPrice;
import com.crown.library.onspotlibrary.utils.ListItemType;

public class BusinessItemV1 extends BusinessItemV0 {
    private OSPrice price;

    public OSPrice getPrice() {
        return price == null ? new OSPrice() : price;
    }

    public void setPrice(OSPrice price) {
        this.price = price;
    }

    @Override
    public int getItemType() {
        return ListItemType.BUSINESS_ITEM_V1;
    }
}
