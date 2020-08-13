package com.crown.library.onspotlibrary.model.businessItem;

import com.crown.library.onspotlibrary.utils.ListItemType;
import com.crown.library.onspotlibrary.utils.emun.BusinessItemStatus;

public class BusinessItemV3 extends BusinessItemV2 {
    private Long onStock;
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
}
