package com.crown.library.onspotlibrary.model.review;

import com.crown.library.onspotlibrary.utils.ListItemType;
import com.crown.library.onspotlibrary.utils.OSString;
import com.crown.library.onspotlibrary.utils.OSTimeUtils;
import com.crown.library.onspotlibrary.utils.emun.OSReviewType;
import com.google.firebase.Timestamp;
import com.google.gson.Gson;

public class OSRUItemByCustomer extends OSReviewV1 {
    private String item;
    private String business;
    private String customer;

    public OSRUItemByCustomer() {
        setReviewType(OSReviewType.ITEM_BY_CUSTOMER);
    }

    public static OSRUItemByCustomer fromJson(String json) {
        OSRUItemByCustomer obj = new Gson().fromJson(json, OSRUItemByCustomer.class);
        Timestamp timestamp = OSTimeUtils.getTimestampFromJsonObj(json, OSString.fieldCreatedOn);
        if (timestamp != null) obj.setCreatedOn(timestamp);
        return obj;
    }

    @Override
    public int getItemType() {
        return ListItemType.OSRU_ITEM_BY_CUSTOMER;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
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
