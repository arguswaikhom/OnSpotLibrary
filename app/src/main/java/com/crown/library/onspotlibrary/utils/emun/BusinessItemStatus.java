package com.crown.library.onspotlibrary.utils.emun;

public enum BusinessItemStatus {
    AVAILABLE, NOT_AVAILABLE, OUT_OF_STOCK;

    public String getName() {
        switch (this) {
            case OUT_OF_STOCK:
                return "Out of stock";
            case NOT_AVAILABLE:
                return "Not available";
            case AVAILABLE:
            default:
                return "Available";
        }
    }
}
