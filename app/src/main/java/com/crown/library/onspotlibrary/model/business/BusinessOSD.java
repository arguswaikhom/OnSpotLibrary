package com.crown.library.onspotlibrary.model.business;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

public class BusinessOSD {
    private String businessRefId;
    private String status;

    public BusinessOSD(String businessRefId, String status) {
        this.businessRefId = businessRefId;
        this.status = status;
    }

    public BusinessOSD() {
    }

    public String getBusinessRefId() {
        return businessRefId;
    }

    public void setBusinessRefId(String businessRefId) {
        this.businessRefId = businessRefId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @NonNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
