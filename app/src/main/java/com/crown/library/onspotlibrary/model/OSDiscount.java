package com.crown.library.onspotlibrary.model;

import com.crown.library.onspotlibrary.utils.emun.OSDiscountType;

public class OSDiscount {
    private OSDiscountType type;
    private Long value;

    public OSDiscountType getType() {
        return type;
    }

    public void setType(OSDiscountType type) {
        this.type = type;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
