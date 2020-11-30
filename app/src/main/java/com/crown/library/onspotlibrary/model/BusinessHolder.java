package com.crown.library.onspotlibrary.model;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

public class BusinessHolder {
    private String role;
    private String userId;

    public BusinessHolder() {
    }

    public BusinessHolder(String role, String userId) {
        this.role = role;
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @NotNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
