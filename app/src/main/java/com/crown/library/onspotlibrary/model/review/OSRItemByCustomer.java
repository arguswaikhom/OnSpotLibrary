package com.crown.library.onspotlibrary.model.review;

import com.crown.library.onspotlibrary.model.business.BusinessV2a;
import com.crown.library.onspotlibrary.model.businessItem.BusinessItemV2;
import com.crown.library.onspotlibrary.model.user.UserV3;
import com.crown.library.onspotlibrary.utils.ListItemType;
import com.crown.library.onspotlibrary.utils.emun.OSReviewType;

public class OSRItemByCustomer extends OSReviewV1 {
    private BusinessItemV2 item;
    private BusinessV2a business;
    private UserV3 customer;

    public OSRItemByCustomer() {
        setReviewType(OSReviewType.ITEM_BY_CUSTOMER);
    }

    @Override
    public int getItemType() {
        return ListItemType.OSR_ITEM_BY_CUSTOMER;
    }

    public BusinessItemV2 getItem() {
        return item;
    }

    public void setItem(BusinessItemV2 item) {
        this.item = item;
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
