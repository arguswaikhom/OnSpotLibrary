package com.crown.library.onspotlibrary.model.user;

import com.crown.library.onspotlibrary.model.OSRating;
import com.crown.library.onspotlibrary.utils.ListItemType;

public class UserV3 extends UserV2 {
    private OSRating deliveryRating;

    @Override
    public int getItemType() {
        return ListItemType.USER_V3;
    }

    public OSRating getDeliveryRating() {
        return deliveryRating;
    }

    public void setDeliveryRating(OSRating deliveryRating) {
        this.deliveryRating = deliveryRating;
    }
}
