package com.crown.library.onspotlibrary.utils;

import com.crown.library.onspotlibrary.utils.emun.BusinessItemPriceUnit;

public class OSBusinessItemUnitUtils {
    public static String getDisplayText(int q, BusinessItemPriceUnit unit) {
        switch (unit) {
            case gm:
                return q == 1 ? "gm" : q + " gm";
            case kg:
                return q == 1 ? "kg" : q + " kg";
            case ml:
                return q == 1 ? "ml" : q + " ml";
            case liter:
                return q == 1 ? "liter" : q + " liter";
            case bunch:
                return q == 1 ? "bunch" : q + " bunches";
            case packet:
                return q != 1 ? q + " packets" : "packet";
            case item:
                return q == 1 ? "item" : q + " items";
            case dozen:
                return q == 1 ? "dozen" : q + " dozens";
            case piece:
                return q == 1 ? "piece" : q + " pieces";
            case meter:
                return q == 1 ? "meter" : q + " meter";
            case ft:
                return q == 1 ? "ft" : q + " ft";
            case sqft:
                return q == 1 ? "sq. ft" : q + " sq. ft";
            case sqmeter:
                return q == 1 ? "sq. meter" : q + " sq. meter";
            case hour:
                return q == 1 ? "hour" : q + " hours";
            case day:
                return q == 1 ? "day" : q + " days";
            case month:
                return q == 1 ? "month" : q + " months";
            case year:
                return q == 1 ? "year" : q + " years";
            case service:
                return q == 1 ? "service" : q + " services";
            default:
                return unit.name();
        }
    }
}
