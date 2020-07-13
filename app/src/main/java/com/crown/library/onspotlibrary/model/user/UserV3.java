package com.crown.library.onspotlibrary.model.user;

import com.crown.library.onspotlibrary.utils.ListItemType;

public class UserV3 extends UserV2 {
    private Boolean hasEmailVerified;
    private Boolean hasPhoneNumberVerified;

    @Override
    public int getItemType() {
        return ListItemType.USER_V3;
    }

    public Boolean getHasEmailVerified() {
        return hasEmailVerified;
    }

    public void setHasEmailVerified(Boolean hasEmailVerified) {
        this.hasEmailVerified = hasEmailVerified;
    }

    public Boolean getHasPhoneNumberVerified() {
        return hasPhoneNumberVerified;
    }

    public void setHasPhoneNumberVerified(Boolean hasPhoneNumberVerified) {
        this.hasPhoneNumberVerified = hasPhoneNumberVerified;
    }
}
