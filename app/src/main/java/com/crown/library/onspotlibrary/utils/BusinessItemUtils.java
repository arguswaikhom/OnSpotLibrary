package com.crown.library.onspotlibrary.utils;

import com.crown.library.onspotlibrary.model.OSDiscount;
import com.crown.library.onspotlibrary.model.OSPrice;

public class BusinessItemUtils {

    /**
     * final price = actual price + tax - discount
     */
    public static double getFinalPrice(double price, double tax, OSDiscount discount) {
        return (price + getTaxAmount(price, tax)) - getDiscountPrice(price, discount);
    }

    public static double getFinalPrice(OSPrice price) {
        return getFinalPrice(price.getPrice(), price.getTax(), price.getDiscount());
    }

    /**
     * get the actual tax from the tax percentage
     */
    public static double getTaxAmount(OSPrice price) {
        return getTaxAmount(price.getPrice(), price.getTax());
    }

    public static double getTaxAmount(double price, double tax) {
        return (price * tax) / 100;
    }

    public static double getDiscountPrice(OSPrice price) {
        return getDiscountPrice(price.getPrice(), price.getDiscount());
    }

    public static double getDiscountPrice(double price, OSDiscount discount) {
        switch (discount.getType()) {
            case PERCENT:
                return (price * discount.getValue()) / 100;
            case PRICE:
                return discount.getValue();
            case NO_DISCOUNT:
            default:
                return 0;
        }
    }
}
