package com.crown.library.onspotlibrary.model.business;

import com.crown.library.onspotlibrary.model.OSShippingCharge;
import com.crown.library.onspotlibrary.utils.ListItemType;

public class BusinessV3 extends BusinessV2a {
    String opensAt;
    String closesAt;
    String openingDays;
    Long deliveryRange;
    Boolean fsAvailable;
    Long minOrder;
    OSShippingCharge shippingCharges;

    @Override
    public int getItemType() {
        return ListItemType.BUSINESS_V3;
    }

    public String getOpensAt() {
        return opensAt;
    }

    public void setOpensAt(String opensAt) {
        this.opensAt = opensAt;
    }

    public String getClosesAt() {
        return closesAt;
    }

    public void setClosesAt(String closesAt) {
        this.closesAt = closesAt;
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
