package com.crown.library.onspotlibrary.model.businessItem;

import com.crown.library.onspotlibrary.utils.ListItemType;
import com.crown.library.onspotlibrary.model.OSDiscount;

public class BusinessItemV1 extends BusinessItemV0 {
    private Long price;
    private Long tax;
    private OSDiscount discount;

    @Override
    public int getItemType() {
        return ListItemType.BUSINESS_ITEM_V1;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getTax() {
        return tax;
    }

    public void setTax(Long tax) {
        this.tax = tax;
    }

    public OSDiscount getDiscount() {
        return discount;
    }

    public void setDiscount(OSDiscount discount) {
        this.discount = discount;
    }
}
