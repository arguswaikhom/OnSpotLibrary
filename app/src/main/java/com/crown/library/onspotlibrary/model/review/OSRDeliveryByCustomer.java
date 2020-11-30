package com.crown.library.onspotlibrary.model.review;

import com.crown.library.onspotlibrary.model.business.BusinessV2a;
import com.crown.library.onspotlibrary.model.order.OSOrder;
import com.crown.library.onspotlibrary.model.user.UserV3;
import com.crown.library.onspotlibrary.utils.ListItemType;
import com.crown.library.onspotlibrary.utils.emun.OSReviewType;

public class OSRDeliveryByCustomer extends OSReviewV1 {
    private UserV3 customer;
    private UserV3 delivery;
    private OSOrder order;
    private BusinessV2a business;

    public OSRDeliveryByCustomer() {
        setReviewType(OSReviewType.DELIVERY_BY_CUSTOMER);
    }

    @Override
    public int getItemType() {
        return ListItemType.OSR_DELIVERY_BY_CUSTOMER;
    }

    public UserV3 getCustomer() {
        return customer;
    }

    public void setCustomer(UserV3 customer) {
        this.customer = customer;
    }

    public UserV3 getDelivery() {
        return delivery;
    }

    public void setDelivery(UserV3 delivery) {
        this.delivery = delivery;
    }

    public OSOrder getOrder() {
        return order;
    }

    public void setOrder(OSOrder order) {
        this.order = order;
    }

    public BusinessV2a getBusiness() {
        return business;
    }

    public void setBusiness(BusinessV2a business) {
        this.business = business;
    }
}
