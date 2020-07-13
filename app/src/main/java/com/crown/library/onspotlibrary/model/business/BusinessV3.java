package com.crown.library.onspotlibrary.model.business;

import com.crown.library.onspotlibrary.utils.ListItemType;
import com.crown.library.onspotlibrary.model.OSTime;

public class BusinessV3 extends BusinessV2 {
    OSTime openingTime;
    OSTime closingTime;
    String openingDays;
    Double deliveryRange;
    Boolean passiveOpenEnable;
    Boolean open;

    @Override
    public int getItemType() {
        return ListItemType.BUSINESS_V3;
    }

    public OSTime getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(OSTime openingTime) {
        this.openingTime = openingTime;
    }

    public OSTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(OSTime closingTime) {
        this.closingTime = closingTime;
    }

    public String getOpeningDays() {
        return openingDays;
    }

    public void setOpeningDays(String openingDays) {
        this.openingDays = openingDays;
    }

    public Double getDeliveryRange() {
        return deliveryRange;
    }

    public void setDeliveryRange(Double deliveryRange) {
        this.deliveryRange = deliveryRange;
    }

    public Boolean getPassiveOpenEnable() {
        return passiveOpenEnable;
    }

    public void setPassiveOpenEnable(Boolean passiveOpenEnable) {
        this.passiveOpenEnable = passiveOpenEnable;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }
}
