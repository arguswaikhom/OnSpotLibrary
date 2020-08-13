package com.crown.library.onspotlibrary.model.cart;

import com.crown.library.onspotlibrary.model.businessItem.BusinessItemV4;
import com.crown.library.onspotlibrary.utils.ListItemType;

public class OSCart extends BusinessItemV4 {
    private long quantity;

    @Override
    public int getItemType() {
        return ListItemType.CART;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
