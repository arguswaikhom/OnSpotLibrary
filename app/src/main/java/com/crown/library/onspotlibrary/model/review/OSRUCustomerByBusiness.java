package com.crown.library.onspotlibrary.model.review;

import com.crown.library.onspotlibrary.utils.ListItemType;
import com.crown.library.onspotlibrary.utils.emun.OSReviewType;

public class OSRUCustomerByBusiness extends OSReviewV1 {
    private String business;
    private String customer;
    private String order;

    public OSRUCustomerByBusiness() {
        setReviewType(OSReviewType.CUSTOMER_BY_BUSINESS);
    }

    @Override
    public int getItemType() {
        return ListItemType.OSRU_CUSTOMER_BY_BUSINESS;
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

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
