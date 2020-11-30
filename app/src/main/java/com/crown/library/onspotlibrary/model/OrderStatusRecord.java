package com.crown.library.onspotlibrary.model;

import com.crown.library.onspotlibrary.utils.emun.OrderStatus;
import com.google.firebase.Timestamp;

public class OrderStatusRecord {
    private OrderStatus status;
    private Timestamp timestamp;

    public OrderStatusRecord() {
    }

    public OrderStatusRecord(OrderStatus status, Timestamp timestamp) {
        this.status = status;
        this.timestamp = timestamp;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
