package com.crown.library.onspotlibrary.model.business;

import androidx.annotation.NonNull;

import com.crown.library.onspotlibrary.model.ListItem;
import com.crown.library.onspotlibrary.utils.ListItemType;
import com.crown.library.onspotlibrary.utils.emun.OSClass;
import com.google.gson.Gson;

public class BusinessV0 extends ListItem {
    String businessRefId;
    String displayName;
    String imageUrl;
    Boolean isOpen;
    OSClass osClass = OSClass.BUSINESS;

    public BusinessV0() {
    }

    public <T extends BusinessV0> BusinessV0(T business) {
        this.businessRefId = business.getBusinessRefId();
        this.displayName = business.getDisplayName();
        this.imageUrl = business.getImageUrl();
    }

    public static <T extends BusinessV0> T fromJson(String json, Class<T> cls) {
        return new Gson().fromJson(json, cls);
    }

    @Override
    public int getItemType() {
        return ListItemType.BUSINESS_V0;
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

    public OSClass getOsClass() {
        return osClass;
    }

    public void setOsClass(OSClass osClass) {
        this.osClass = osClass;
    }

    public Boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Boolean open) {
        isOpen = open;
    }

    @NonNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
