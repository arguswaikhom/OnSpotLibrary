package com.crown.library.onspotlibrary.model;

import com.crown.library.onspotlibrary.model.business.BusinessV0;
import com.crown.library.onspotlibrary.model.cart.OSCartLite;
import com.crown.library.onspotlibrary.model.user.UserV2;
import com.crown.library.onspotlibrary.utils.ListItemType;
import com.crown.library.onspotlibrary.utils.emun.OrderStatus;

import java.util.List;

public class OSOrder extends ListItem {
    private String orderId;
    private Long totalPrice;
    private Long finalPrice;
    private List<OSCartLite> items;
    private BusinessV0 business;
    private UserV2 customer;
    private UserV2 delivery;
    private OrderStatus status;

    @Override
    public int getItemType() {
        return ListItemType.OS_ORDER;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Long finalPrice) {
        this.finalPrice = finalPrice;
    }

    public List<OSCartLite> getItems() {
        return items;
    }

    public void setItems(List<OSCartLite> items) {
        this.items = items;
    }

    public BusinessV0 getBusiness() {
        return business;
    }

    public void setBusiness(BusinessV0 business) {
        this.business = business;
    }

    public UserV2 getCustomer() {
        return customer;
    }

    public void setCustomer(UserV2 customer) {
        this.customer = customer;
    }

    public UserV2 getDelivery() {
        return delivery;
    }

    public void setDelivery(UserV2 delivery) {
        this.delivery = delivery;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
