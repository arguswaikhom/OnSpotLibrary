package com.crown.library.onspotlibrary.model.cart;

import com.crown.library.onspotlibrary.utils.ListItemType;
import com.crown.library.onspotlibrary.model.businessItem.BusinessItemV4;

public class OSCart extends BusinessItemV4 {
    private Long quantity = 0L;

    @Override
    public int getItemType() {
        return ListItemType.CART;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
