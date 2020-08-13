package com.crown.library.onspotlibrary.model.cart;

import com.crown.library.onspotlibrary.model.businessItem.BusinessItemV1;
import com.crown.library.onspotlibrary.utils.ListItemType;

public class OSCartLite extends BusinessItemV1 {
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
