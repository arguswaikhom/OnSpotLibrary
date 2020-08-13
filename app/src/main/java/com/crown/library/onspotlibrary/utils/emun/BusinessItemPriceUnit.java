package com.crown.library.onspotlibrary.utils.emun;

import java.util.Arrays;
import java.util.List;

public enum BusinessItemPriceUnit {
    gm, kg, ml, liter, pack, item;

    public static List<String> getStringAll() {
        return Arrays.asList(gm.name(), kg.name(), ml.name(), liter.name(), pack.name(), item.name());
    }
}
