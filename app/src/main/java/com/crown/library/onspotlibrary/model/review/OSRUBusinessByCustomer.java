package com.crown.library.onspotlibrary.model.review;

import com.crown.library.onspotlibrary.utils.ListItemType;
import com.crown.library.onspotlibrary.utils.emun.OSReviewType;

public class OSRUBusinessByCustomer extends OSReviewV1 {
    private String business;
    private String customer;

    public OSRUBusinessByCustomer() {
        setReviewType(OSReviewType.BUSINESS_BY_CUSTOMER);
    }

    @Override
    public int getItemType() {
        return ListItemType.OSRU_BUSINESS_BY_CUSTOMER;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
}
