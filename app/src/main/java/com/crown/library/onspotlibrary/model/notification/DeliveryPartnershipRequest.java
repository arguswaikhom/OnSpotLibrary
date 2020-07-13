package com.crown.library.onspotlibrary.model.notification;

import androidx.annotation.NonNull;

import com.crown.library.onspotlibrary.model.ListItem;
import com.crown.library.onspotlibrary.model.business.BusinessV0;
import com.crown.library.onspotlibrary.model.user.UserV1;
import com.crown.library.onspotlibrary.utils.ListItemType;
import com.crown.library.onspotlibrary.utils.emun.BusinessRequestStatus;
import com.google.gson.Gson;

public class DeliveryPartnershipRequest extends ListItem {

    private String id;
    private UserV1 osd;
    private BusinessV0 osb;
    private BusinessRequestStatus status;

    @Override
    public int getItemType() {
        return ListItemType.DELIVERY_PARTNERSHIP_REQUEST;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserV1 getOsd() {
        return osd;
    }

    public void setOsd(UserV1 osd) {
        this.osd = osd;
    }

    public BusinessV0 getOsb() {
        return osb;
    }

    public void setOsb(BusinessV0 osb) {
        this.osb = osb;
    }

    public BusinessRequestStatus getStatus() {
        return status;
    }

    public void setStatus(BusinessRequestStatus status) {
        this.status = status;
    }

    @NonNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
