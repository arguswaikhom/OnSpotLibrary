package com.crown.library.onspotlibrary.utils.emun;

import android.content.Context;

import com.crown.library.onspotlibrary.R;

public enum OrderStatus {
    ORDERED, ACCEPTED, PREPARING, READY, ON_THE_WAY, DELIVERED, CANCELED;

    public String getStatus() {
        switch (this) {
            case ORDERED: return "New Order";
            case ACCEPTED: return "Accepted";
            case PREPARING: return "Preparing";
            case READY: return "Order Ready";
            case ON_THE_WAY: return "Out For Deliver";
            case DELIVERED: return "Delivered";
            case CANCELED: return "Canceled";
            default: return "";
        }
    }

    public String getButtonText() {
        switch (this) {
            case ORDERED: return "Accept order";
            case ACCEPTED: return "Prepare order";
            case PREPARING: return "Order ready";
            case READY: return "Out For Deliver";
            case CANCELED: return "Canceled";
            case ON_THE_WAY: case DELIVERED: default: return "Update to Delivered";
        }
    }

    public int getColor(Context context) {
        switch (this) {
            case ORDERED: return context.getColor(R.color.order_status_ordered);
            case ACCEPTED: return context.getColor(R.color.order_status_accepted);
            case PREPARING: return context.getColor(R.color.order_status_preparing);
            case READY: return context.getColor(R.color.order_status_ready);
            case ON_THE_WAY: return context.getColor(R.color.order_status_out_for_deliver);
            case CANCELED: return context.getColor(R.color.order_status_canceled);
            case DELIVERED: return context.getColor(R.color.order_status_delivered);
            default: return 0;
        }
    }
}
