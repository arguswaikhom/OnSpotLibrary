package com.crown.library.onspotlibrary.model.review;

import com.crown.library.onspotlibrary.utils.ListItemType;
import com.crown.library.onspotlibrary.utils.OSString;
import com.crown.library.onspotlibrary.utils.OSTimeUtils;
import com.crown.library.onspotlibrary.utils.emun.OSReviewType;
import com.google.firebase.Timestamp;
import com.google.gson.Gson;

public class OSRUDeliveryByCustomer extends OSReviewV1 {
    private String customer;
    private String delivery;
    private String order;
    private String business;

    public OSRUDeliveryByCustomer() {
        setReviewType(OSReviewType.DELIVERY_BY_CUSTOMER);
    }

    public static OSRUDeliveryByCustomer fromJson(String json) {
        OSRUDeliveryByCustomer obj = new Gson().fromJson(json, OSRUDeliveryByCustomer.class);
        Timestamp timestamp = OSTimeUtils.getTimestampFromJsonObj(json, OSString.fieldCreatedOn);
        if (timestamp != null) obj.setCreatedOn(timestamp);
        return obj;
    }

    @Override
    public int getItemType() {
        return ListItemType.OSRU_DELIVERY_BY_CUSTOMER;
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
