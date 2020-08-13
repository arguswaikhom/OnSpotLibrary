package com.crown.library.onspotlibrary.model;

import com.crown.library.onspotlibrary.utils.emun.BusinessItemPriceUnit;

public class OSPrice {
    private Long price;
    private Long tax;
    private OSDiscount discount;
    private BusinessItemPriceUnit unit;
    private Long quantity;

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
        return discount == null ? new OSDiscount() : discount;
    }

    public void setDiscount(OSDiscount discount) {
        this.discount = discount;
    }

    public BusinessItemPriceUnit getUnit() {
        return unit;
    }

    public void setUnit(BusinessItemPriceUnit unit) {
        this.unit = unit;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
