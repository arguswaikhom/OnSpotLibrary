package com.crown.library.onspotlibrary.model.user;

import com.crown.library.onspotlibrary.model.ListItem;
import com.crown.library.onspotlibrary.utils.ListItemType;
import com.crown.library.onspotlibrary.utils.emun.OSClass;

public class UserV0 extends ListItem {
    private String displayName;
    private String profileImageUrl;
    private String userId;
    private OSClass osClass = OSClass.USER;

    @Override
    public int getItemType() {
        return ListItemType.USER_V0;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public OSClass getOsClass() {
        return osClass;
    }

    public void setOsClass(OSClass osClass) {
        this.osClass = osClass;
    }
}
