package com.crown.library.onspotlibrary.model.businessItem;

import com.crown.library.onspotlibrary.utils.ListItemType;

public class BusinessItemV2 extends BusinessItemV1 {
    private String category;
    private String description;
    private String imageUrl;

    @Override
    public int getItemType() {
        return ListItemType.BUSINESS_ITEM_V2;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}