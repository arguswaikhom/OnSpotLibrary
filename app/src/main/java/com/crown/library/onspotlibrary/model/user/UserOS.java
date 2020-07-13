package com.crown.library.onspotlibrary.model.user;

import java.util.List;

public class UserOS extends UserV3 {
    private Boolean hasOnSpotAccount;
    private List<String> deviceToken;

    public Boolean getHasOnSpotAccount() {
        return hasOnSpotAccount;
    }

    public void setHasOnSpotAccount(Boolean hasOnSpotAccount) {
        this.hasOnSpotAccount = hasOnSpotAccount;
    }

    public List<String> getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(List<String> deviceToken) {
        this.deviceToken = deviceToken;
    }
}
