package com.crown.library.onspotlibrary.model.business;

import com.crown.library.onspotlibrary.model.BusinessHolder;
import com.crown.library.onspotlibrary.model.DeliveryPartnerOSB;
import com.crown.library.onspotlibrary.utils.ListItemType;
import com.google.firebase.Timestamp;

import java.util.List;

public class BusinessV5 extends BusinessV4 {
    Timestamp createdOn;
    String creator;
    List<BusinessHolder> holder;
    List<DeliveryPartnerOSB> osd;

    @Override
    public int getItemType() {
        return ListItemType.BUSINESS_V5;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public List<BusinessHolder> getHolder() {
        return holder;
    }

    public void setHolder(List<BusinessHolder> holder) {
        this.holder = holder;
    }

    public List<DeliveryPartnerOSB> getOsd() {
        return osd;
    }

    public void setOsd(List<DeliveryPartnerOSB> osd) {
        this.osd = osd;
    }
}
