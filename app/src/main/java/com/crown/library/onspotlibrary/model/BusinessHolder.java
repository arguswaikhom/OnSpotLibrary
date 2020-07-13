package com.crown.library.onspotlibrary.model;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

public class BusinessHolder {
    private String role;
    private String userId;

    public BusinessHolder() {
    }

    public String getRole() {
        return role;
    }

    public String getUserId() {
        return userId;
    }

    @NotNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
