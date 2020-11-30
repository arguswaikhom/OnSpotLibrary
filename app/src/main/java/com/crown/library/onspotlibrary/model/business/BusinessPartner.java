package com.crown.library.onspotlibrary.model.business;

import com.crown.library.onspotlibrary.utils.ListItemType;
import com.crown.library.onspotlibrary.utils.emun.BusinessRequestStatus;

public class BusinessPartner extends BusinessV2 {
    private BusinessV2 business;
    private BusinessRequestStatus status;

    public BusinessPartner() {
    }

    public BusinessPartner(BusinessV2 business, BusinessRequestStatus status) {
        this.business = business;
        this.status = status;
    }

    @Override
    public int getItemType() {
        return ListItemType.BUSINESS_PARTNER;
    }

    public BusinessV2 getBusiness() {
        return business;
    }

    public void setBusiness(BusinessV2 business) {
        this.business = business;
    }

    public BusinessRequestStatus getStatus() {
        return status;
    }

    public void setStatus(BusinessRequestStatus status) {
        this.status = status;
    }
}
