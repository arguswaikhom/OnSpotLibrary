package com.crown.library.onspotlibrary.model.businessItem;

import com.crown.library.onspotlibrary.model.ListItem;
import com.crown.library.onspotlibrary.utils.ListItemType;
import com.crown.library.onspotlibrary.utils.emun.OSClass;

public class BusinessItemV0 extends ListItem {
    private String itemId;
    private String itemName;
    private String businessRefId;
    private OSClass osClass = OSClass.PRODUCT;

    @Override
    public int getItemType() {
        return ListItemType.BUSINESS_ITEM_V0;
    }

    public void setItemType(int itemType) {
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getBusinessRefId() {
        return businessRefId;
    }

    public void setBusinessRefId(String businessRefId) {
        this.businessRefId = businessRefId;
    }

    public OSClass getOsClass() {
        return osClass;
    }

    public void setOsClass(OSClass osClass) {
        this.osClass = osClass;
    }
}
