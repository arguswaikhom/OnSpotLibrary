package com.crown.library.onspotlibrary.model.businessItem;

import com.crown.library.onspotlibrary.utils.emun.BusinessItemStatus;
import com.crown.library.onspotlibrary.utils.ListItemType;

public class BusinessItemV3 extends BusinessItemV2 {
    private Long onStock;
    private Boolean isDeleted;
    private BusinessItemStatus status;

    @Override
    public int getItemType() {
        return ListItemType.BUSINESS_ITEM_V3;
    }

    public Long getOnStock() {
        return onStock;
    }

    public void setOnStock(Long onStock) {
        this.onStock = onStock;
    }

    public BusinessItemStatus getStatus() {
        return status;
    }

    public void setStatus(BusinessItemStatus status) {
        this.status = status;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
