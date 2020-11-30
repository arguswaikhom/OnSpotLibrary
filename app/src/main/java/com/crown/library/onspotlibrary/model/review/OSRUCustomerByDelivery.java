package com.crown.library.onspotlibrary.model.review;

import com.crown.library.onspotlibrary.utils.ListItemType;
import com.crown.library.onspotlibrary.utils.emun.OSReviewType;

public class OSRUCustomerByDelivery extends OSReviewV1 {
    private String customer;
    private String delivery;
    private String order;
    private String business;

    public OSRUCustomerByDelivery() {
        setReviewType(OSReviewType.CUSTOMER_BY_DELIVERY);
    }

    @Override
    public int getItemType() {
        return ListItemType.OSRU_CUSTOMER_BY_DELIVERY;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }
}
