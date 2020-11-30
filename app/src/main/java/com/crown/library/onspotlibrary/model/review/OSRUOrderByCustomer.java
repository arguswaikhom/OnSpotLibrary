package com.crown.library.onspotlibrary.model.review;

import com.crown.library.onspotlibrary.utils.ListItemType;
import com.crown.library.onspotlibrary.utils.OSString;
import com.crown.library.onspotlibrary.utils.OSTimeUtils;
import com.crown.library.onspotlibrary.utils.emun.OSReviewType;
import com.google.firebase.Timestamp;
import com.google.gson.Gson;

public class OSRUOrderByCustomer extends OSReviewV1 {
    private String order;
    private String customer;
    private String business;

    public OSRUOrderByCustomer() {
        setReviewType(OSReviewType.ORDER_BY_CUSTOMER);
    }

    public static OSRUOrderByCustomer fromJson(String json) {
        OSRUOrderByCustomer obj = new Gson().fromJson(json, OSRUOrderByCustomer.class);
        Timestamp timestamp = OSTimeUtils.getTimestampFromJsonObj(json, OSString.fieldCreatedOn);
        if (timestamp != null) obj.setCreatedOn(timestamp);
        return obj;
    }

    @Override
    public int getItemType() {
        return ListItemType.OSRU_ORDER_BY_CUSTOMER;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }
}
