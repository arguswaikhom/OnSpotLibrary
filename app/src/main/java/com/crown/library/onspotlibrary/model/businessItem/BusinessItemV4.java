package com.crown.library.onspotlibrary.model.businessItem;

import com.crown.library.onspotlibrary.utils.ListItemType;

import java.util.List;

public class BusinessItemV4 extends BusinessItemV3 {
    private List<String> imageUrls;

    @Override
    public int getItemType() {
        return ListItemType.BUSINESS_ITEM_V4;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }
}
