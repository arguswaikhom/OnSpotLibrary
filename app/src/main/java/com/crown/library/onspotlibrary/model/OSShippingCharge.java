package com.crown.library.onspotlibrary.model;

public class OSShippingCharge {
    private Long perOrder;
    private Long freeShippingDistance;
    private Long freeShippingPrice;

    public OSShippingCharge() {
    }

    public Long getPerOrder() {
        return perOrder;
    }

    public void setPerOrder(Long perOrder) {
        this.perOrder = perOrder;
    }

    public Long getFreeShippingDistance() {
        return freeShippingDistance;
    }

    public void setFreeShippingDistance(Long freeShippingDistance) {
        this.freeShippingDistance = freeShippingDistance;
    }

    public Long getFreeShippingPrice() {
        return freeShippingPrice;
    }

    public void setFreeShippingPrice(Long freeShippingPrice) {
        this.freeShippingPrice = freeShippingPrice;
    }
}
