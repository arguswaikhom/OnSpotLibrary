package com.crown.library.onspotlibrary.model.review;

import com.crown.library.onspotlibrary.model.business.BusinessV2a;
import com.crown.library.onspotlibrary.model.user.UserV3;
import com.crown.library.onspotlibrary.utils.ListItemType;
import com.crown.library.onspotlibrary.utils.emun.OSReviewType;

public class OSRCustomerByBusiness extends OSReviewV1 {
    private BusinessV2a business;
    private UserV3 customer;
    private UserV3 order;

    public OSRCustomerByBusiness() {
        setReviewType(OSReviewType.CUSTOMER_BY_BUSINESS);
    }

    @Override
    public int getItemType() {
        return ListItemType.OSR_CUSTOMER_BY_BUSINESS;
    }

    public BusinessV2a getBusiness() {
        return business;
    }

    public void setBusiness(BusinessV2a business) {
        this.business = business;
    }

    public UserV3 getCustomer() {
        return customer;
    }

    public void setCustomer(UserV3 customer) {
        this.customer = customer;
    }

    public UserV3 getOrder() {
        return order;
    }

    public void setOrder(UserV3 order) {
        this.order = order;
    }
}
