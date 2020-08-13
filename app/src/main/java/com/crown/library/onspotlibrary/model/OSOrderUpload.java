package com.crown.library.onspotlibrary.model;

import androidx.annotation.NonNull;

import com.crown.library.onspotlibrary.model.business.BusinessOrder;
import com.crown.library.onspotlibrary.model.cart.OSCartLite;
import com.crown.library.onspotlibrary.model.user.UserOrder;
import com.crown.library.onspotlibrary.utils.ListItemType;
import com.crown.library.onspotlibrary.utils.emun.OrderStatus;
import com.google.firebase.Timestamp;
import com.google.gson.Gson;

import java.util.List;

// *os done
@Deprecated
public class OSOrderUpload extends ListItem {
    private Long totalPrice;
    private Long finalPrice;
    private List<OSCartLite> items;
    private BusinessOrder business;
    private UserOrder customer;
    private UserOrder delivery;
    private OrderStatus status;
    private Timestamp orderedAt;
    private List<OrderStatusRecord> statusRecord;


    @NonNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public List<OrderStatusRecord> getStatusRecord() {
        return statusRecord;
    }

    public void setStatusRecord(List<OrderStatusRecord> statusRecord) {
        this.statusRecord = statusRecord;
    }

    public Timestamp getOrderedAt() {
        if (orderedAt == null) for (OrderStatusRecord status : statusRecord) {
            if (status.getStatus().equals(OrderStatus.ORDERED)) {
                orderedAt = status.getTimestamp();
                break;
            }
        }
        return orderedAt;
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

    public BusinessOrder getBusiness() {
        return business;
    }

    public void setBusiness(BusinessOrder business) {
        this.business = business;
    }

    public UserOrder getCustomer() {
        return customer;
    }

    public void setCustomer(UserOrder customer) {
        this.customer = customer;
    }

    public UserOrder getDelivery() {
        return delivery;
    }

    public void setDelivery(UserOrder delivery) {
        this.delivery = delivery;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setOrderedAt(Timestamp orderedAt) {
        this.orderedAt = orderedAt;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public int getItemType() {
        return ListItemType.OS_ORDER_UPLOAD;
    }
}
