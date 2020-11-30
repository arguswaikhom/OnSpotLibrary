package com.crown.library.onspotlibrary.model.business;

import com.crown.library.onspotlibrary.utils.ListItemType;

import java.util.List;

public class BusinessV4 extends BusinessV3 {
    List<String> imageUrls;
    List<String> productCategories;

    @Override
    public int getItemType() {
        return ListItemType.BUSINESS_V4;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public List<String> getProductCategories() {
        return productCategories;
    }

    public void setProductCategories(List<String> productCategories) {
        this.productCategories = productCategories;
    }
}
