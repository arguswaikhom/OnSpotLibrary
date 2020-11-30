package com.crown.library.onspotlibrary.model.httpresponse;

import com.google.gson.Gson;

public class OSHttpResponseV0 {
    private int status;
    private String message;

    public OSHttpResponseV0() {
    }

    public static OSHttpResponseV0 fromJson(String json) {
        return new Gson().fromJson(json, OSHttpResponseV0.class);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
