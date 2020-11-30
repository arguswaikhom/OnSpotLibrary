package com.crown.library.onspotlibrary.model.notification;

import androidx.annotation.NonNull;

import com.crown.library.onspotlibrary.utils.ListItemType;
import com.crown.library.onspotlibrary.utils.emun.BusinessRequestStatus;
import com.google.gson.Gson;

public class OSDeliveryPartnershipRequest extends OSNotificationV0 {
    private String osd;
    private String osb;
    private BusinessRequestStatus status;

    @Override
    public int getItemType() {
        return ListItemType.NOTI_DELIVERY_PARTNERSHIP_REQUEST;
    }

    public String getOsd() {
        return osd;
    }

    public void setOsd(String osd) {
        this.osd = osd;
    }

    public String getOsb() {
        return osb;
    }

    public void setOsb(String osb) {
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
