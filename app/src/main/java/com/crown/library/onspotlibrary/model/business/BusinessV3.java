package com.crown.library.onspotlibrary.model.business;

import com.crown.library.onspotlibrary.model.OSShippingCharge;
import com.crown.library.onspotlibrary.model.OSTime;
import com.crown.library.onspotlibrary.utils.ListItemType;

public class BusinessV3 extends BusinessV2a {
    OSTime openingTime;
    OSTime closingTime;
    String openingDays;
    Long deliveryRange;
    Boolean fsAvailable;
    Long minOrder;
    OSShippingCharge shippingCharges;

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

    public Long getDeliveryRange() {
        return deliveryRange;
    }

    public void setDeliveryRange(Long deliveryRange) {
        this.deliveryRange = deliveryRange;
    }

    public Boolean getFsAvailable() {
        return fsAvailable;
    }

    public void setFsAvailable(Boolean fsAvailable) {
        this.fsAvailable = fsAvailable;
    }

    public Long getMinOrder() {
        return minOrder;
    }

    public void setMinOrder(Long minOrder) {
        this.minOrder = minOrder;
    }

    public OSShippingCharge getShippingCharges() {
        return shippingCharges;
    }

    public void setShippingCharges(OSShippingCharge shippingCharges) {
        this.shippingCharges = shippingCharges;
    }
}
