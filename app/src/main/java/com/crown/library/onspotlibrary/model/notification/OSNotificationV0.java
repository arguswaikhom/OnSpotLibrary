package com.crown.library.onspotlibrary.model.notification;

import com.crown.library.onspotlibrary.model.ListItem;
import com.crown.library.onspotlibrary.utils.ListItemType;
import com.google.firebase.Timestamp;

public class OSNotificationV0 extends ListItem {
    private String id;
    private Timestamp createdAt;

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int getItemType() {
        return ListItemType.NOTIFICATION_V0;
    }
}
