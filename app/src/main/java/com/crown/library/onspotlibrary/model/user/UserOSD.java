package com.crown.library.onspotlibrary.model.user;

import com.crown.library.onspotlibrary.model.business.BusinessOSD;

import java.util.List;

public class UserOSD extends UserV4 {
    private Boolean hasOnSpotDeliveryAccount;
    private List<BusinessOSD> businessOSD;
    private List<String> deviceTokenOSD;

    public List<BusinessOSD> getBusinessOSD() {
        return businessOSD;
    }

    public void setBusinessOSD(List<BusinessOSD> businessOSD) {
        this.businessOSD = businessOSD;
    }

    public Boolean getHasOnSpotDeliveryAccount() {
        return hasOnSpotDeliveryAccount;
    }

    public void setHasOnSpotDeliveryAccount(Boolean hasOnSpotDeliveryAccount) {
        this.hasOnSpotDeliveryAccount = hasOnSpotDeliveryAccount;
    }

    public List<String> getDeviceTokenOSD() {
        return deviceTokenOSD;
    }

    public void setDeviceTokenOSD(List<String> deviceTokenOSD) {
        this.deviceTokenOSD = deviceTokenOSD;
    }
}
