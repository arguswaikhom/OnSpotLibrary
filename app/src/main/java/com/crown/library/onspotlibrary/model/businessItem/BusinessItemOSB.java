package com.crown.library.onspotlibrary.model.businessItem;

import com.crown.library.onspotlibrary.utils.ListItemType;

public class BusinessItemOSB extends BusinessItemV4 {
    private boolean archived;
    private boolean adminBlocked;
    private boolean isActive;

    @Override
    public int getItemType() {
        return ListItemType.BUSINESS_ITEM_OSB;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public boolean isAdminBlocked() {
        return adminBlocked;
    }

    public void setAdminBlocked(boolean adminBlocked) {
        this.adminBlocked = adminBlocked;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }
}
