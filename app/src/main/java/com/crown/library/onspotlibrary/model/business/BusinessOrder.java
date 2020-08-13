package com.crown.library.onspotlibrary.model.business;

import com.crown.library.onspotlibrary.model.OSLocation;

public class BusinessOrder {
    String businessRefId;
    String displayName;
    OSLocation location;

    public static <T extends BusinessV2> BusinessOrder fromBusiness(T business) {
        BusinessOrder businessOrder = new BusinessOrder();
        businessOrder.setBusinessRefId(business.getBusinessRefId());
        businessOrder.setDisplayName(business.getDisplayName());
        businessOrder.setLocation(business.getLocation());
        return businessOrder;
    }

    public String getBusinessRefId() {
        return businessRefId;
    }

    public void setBusinessRefId(String businessRefId) {
        this.businessRefId = businessRefId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public OSLocation getLocation() {
        return location;
    }

    public void setLocation(OSLocation location) {
        this.location = location;
    }
}
