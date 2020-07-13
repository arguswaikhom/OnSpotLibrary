package com.crown.library.onspotlibrary.model.business;

import androidx.annotation.NonNull;

import com.crown.library.onspotlibrary.utils.ListItemType;
import com.crown.library.onspotlibrary.model.OSLocation;
import com.google.gson.Gson;

import java.util.List;

public class BusinessV2 extends BusinessV1 {
    String businessType;
    List<String> businessTypes;
    OSLocation location;

    @Override
    public int getItemType() {
        return ListItemType.BUSINESS_V2;
    }

    @NonNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public List<String> getBusinessTypes() {
        return businessTypes;
    }

    public void setBusinessTypes(List<String> businessTypes) {
        this.businessTypes = businessTypes;
    }

    public OSLocation getLocation() {
        return location;
    }

    public void setLocation(OSLocation location) {
        this.location = location;
    }
}
