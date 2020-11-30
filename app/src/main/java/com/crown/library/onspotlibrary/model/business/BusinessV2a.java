package com.crown.library.onspotlibrary.model.business;

import com.crown.library.onspotlibrary.model.OSRating;
import com.crown.library.onspotlibrary.utils.ListItemType;

public class BusinessV2a extends BusinessV2 {
    public OSRating businessRating;
    public OSRating orderRating;

    @Override
    public int getItemType() {
        return ListItemType.BUSINESS_V2A;
    }

    public OSRating getBusinessRating() {
        return businessRating;
    }

    public void setBusinessRating(OSRating businessRating) {
        this.businessRating = businessRating;
    }

    public OSRating getOrderRating() {
        return orderRating;
    }

    public void setOrderRating(OSRating orderRating) {
        this.orderRating = orderRating;
    }
}
