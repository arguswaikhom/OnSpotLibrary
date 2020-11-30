package com.crown.library.onspotlibrary.utils.emun;

import java.util.Arrays;
import java.util.List;

public enum BusinessItemPriceUnit {
    gm, kg, ml, liter, bunch, packet, item, dozen, piece, meter, ft, sqft, sqmeter, hour, day, month, year, service;

    public static List<String> getStringAll() {
        return Arrays.asList(gm.name(), kg.name(), ml.name(), liter.name(), packet.name(), item.name());
    }
}
