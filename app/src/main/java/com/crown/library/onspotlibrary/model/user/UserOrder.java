package com.crown.library.onspotlibrary.model.user;

import com.crown.library.onspotlibrary.model.OSLocation;

public class UserOrder {
    private String displayName;
    private String userId;
    private OSLocation location;

    public UserOrder() {
    }

    public UserOrder(String userId, String displayName, OSLocation location) {
        this.displayName = displayName;
        this.userId = userId;
        this.location = location;
    }

    public static <T extends UserV2> UserOrder fromUser(T user) {
        UserOrder userOrder = new UserOrder();
        userOrder.setUserId(user.getUserId());
        userOrder.setDisplayName(user.getDisplayName());
        userOrder.setLocation(user.getLocation());
        return userOrder;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public OSLocation getLocation() {
        return location;
    }

    public void setLocation(OSLocation location) {
        this.location = location;
    }
}
