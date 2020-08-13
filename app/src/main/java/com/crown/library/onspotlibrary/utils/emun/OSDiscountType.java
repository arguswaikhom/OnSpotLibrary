package com.crown.library.onspotlibrary.utils.emun;

public enum OSDiscountType {
    NO_DISCOUNT, PERCENT, PRICE;

    public String getName() {
        switch (this) {
            case PRICE:
                return "Price";
            case PERCENT:
                return "Percent";
            case NO_DISCOUNT:
            default:
                return "No Discount";
        }
    }
}
