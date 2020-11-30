package com.crown.library.onspotlibrary.model.review;

import com.crown.library.onspotlibrary.model.business.BusinessV2a;
import com.crown.library.onspotlibrary.model.user.UserV3;
import com.crown.library.onspotlibrary.utils.ListItemType;
import com.crown.library.onspotlibrary.utils.emun.OSReviewType;

public class OSRBusinessByCustomer extends OSReviewV1 {
    private BusinessV2a business;
    private UserV3 customer;

    public OSRBusinessByCustomer() {
        setReviewType(OSReviewType.BUSINESS_BY_CUSTOMER);
    }

    @Override
    public int getItemType() {
        return ListItemType.OSR_BUSINESS_BY_CUSTOMER;
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
}
