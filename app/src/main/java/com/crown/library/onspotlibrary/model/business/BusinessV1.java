package com.crown.library.onspotlibrary.model.business;

import com.crown.library.onspotlibrary.utils.ListItemType;

public class BusinessV1 extends BusinessV0 {
    String mobileNumber;
    String website;
    String email;

    @Override
    public int getItemType() {
        return ListItemType.BUSINESS_V1;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
