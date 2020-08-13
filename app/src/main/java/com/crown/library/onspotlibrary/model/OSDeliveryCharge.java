package com.crown.library.onspotlibrary.model;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

public class OSDeliveryCharge {
    Integer index;
    Integer from;
    Integer to;
    Integer charge;

    public OSDeliveryCharge() {
    }

    public OSDeliveryCharge(Integer index, Integer from, Integer to, Integer charge) {
        this.index = index;
        this.from = from;
        this.to = to;
        this.charge = charge;
    }

    @NonNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public Integer getCharge() {
        return charge;
    }

    public void setCharge(Integer charge) {
        this.charge = charge;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
