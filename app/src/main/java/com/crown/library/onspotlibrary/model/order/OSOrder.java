package com.crown.library.onspotlibrary.model.order;

import androidx.annotation.NonNull;

import com.crown.library.onspotlibrary.utils.ListItemType;
import com.google.gson.Gson;

public class OSOrder extends OSOrderUpload {
    private String orderId;
    private Boolean hodAvailable;
    private Long productPrice;
    private Long totalTax;
    private Long totalQuantity;
    private Long totalDiscount;
    private Long shippingCharge;

    public OSOrder() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public int getItemType() {
        return ListItemType.OS_ORDER;
    }

    public Long getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Long totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Boolean getHodAvailable() {
        return hodAvailable != null && hodAvailable;
    }

    public void setHodAvailable(Boolean hodAvailable) {
        this.hodAvailable = hodAvailable;
    }

    public Long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
    }

    public Long getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(Long totalTax) {
        this.totalTax = totalTax;
    }

    public Long getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(Long totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public Long getShippingCharge() {
        return shippingCharge;
    }

    public void setShippingCharge(Long shippingCharge) {
        this.shippingCharge = shippingCharge;
    }

    @NonNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
