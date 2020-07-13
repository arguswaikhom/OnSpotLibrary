package com.crown.library.onspotlibrary.model.business;

import androidx.annotation.NonNull;

import com.crown.library.onspotlibrary.model.ListItem;
import com.crown.library.onspotlibrary.utils.ListItemType;
import com.google.gson.Gson;

public class BusinessV0 extends ListItem {
    String businessId;
    String businessRefId;
    String displayName;
    String imageUrl;

    @Override
    public int getItemType() {
        return ListItemType.BUSINESS_V0;
    }

    public BusinessV0() {
    }

    public <T extends BusinessV0> BusinessV0(T business) {
        this.businessId = business.getBusinessId();
        this.businessRefId = business.getBusinessRefId();
        this.displayName = business.getDisplayName();
        this.imageUrl = business.getImageUrl();
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getBusinessRefId() {
        return businessRefId;
    }

    public void setBusinessRefId(String businessRefId) {
        this.businessRefId = businessRefId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @NonNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
