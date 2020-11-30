package com.crown.library.onspotlibrary.model.review;

import com.crown.library.onspotlibrary.model.order.OSOrder;
import com.crown.library.onspotlibrary.model.user.UserV3;
import com.crown.library.onspotlibrary.utils.ListItemType;
import com.crown.library.onspotlibrary.utils.emun.OSReviewType;

public class OSROrderByCustomer extends OSReviewV1 {
    private OSOrder order;
    private UserV3 customer;
    private UserV3 business;

    public OSROrderByCustomer() {
        setReviewType(OSReviewType.ORDER_BY_CUSTOMER);
    }

    @Override
    public int getItemType() {
        return ListItemType.OSR_ORDER_BY_CUSTOMER;
    }

    public OSOrder getOrder() {
        return order;
    }

    public void setOrder(OSOrder order) {
        this.order = order;
    }

    public UserV3 getCustomer() {
        return customer;
    }

    public void setCustomer(UserV3 customer) {
        this.customer = customer;
    }

    public UserV3 getBusiness() {
        return business;
    }

    public void setBusiness(UserV3 business) {
        this.business = business;
    }
}
