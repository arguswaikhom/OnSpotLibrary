package com.crown.library.onspotlibrary.model.user;

public class UserOSB extends UserV4 {
    private String businessId;
    private String businessRefId;
    private Boolean hasOnSpotBusinessAccount;

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

    public Boolean getHasOnSpotBusinessAccount() {
        return hasOnSpotBusinessAccount;
    }

    public void setHasOnSpotBusinessAccount(Boolean hasOnSpotBusinessAccount) {
        this.hasOnSpotBusinessAccount = hasOnSpotBusinessAccount;
    }
}
