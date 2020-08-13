package com.crown.library.onspotlibrary.model.user;

import com.crown.library.onspotlibrary.model.OSLocation;
import com.crown.library.onspotlibrary.utils.ListItemType;

public class UserV2 extends UserV1 {
    private OSLocation location;

    @Override
    public int getItemType() {
        return ListItemType.USER_V2;
    }

    public OSLocation getLocation() {
        return location;
    }

    public void setLocation(OSLocation location) {
        this.location = location;
    }
}
