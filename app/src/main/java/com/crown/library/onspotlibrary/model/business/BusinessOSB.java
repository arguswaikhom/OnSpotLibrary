package com.crown.library.onspotlibrary.model.business;

public class BusinessOSB extends BusinessV6 {
    private Boolean isActive;

    public BusinessOSB() {
    }

    public Boolean getIsActive() {
        return isActive == null ? true : isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }
}
