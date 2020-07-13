package com.crown.library.onspotlibrary.model.user;

import androidx.annotation.NonNull;

import com.crown.library.onspotlibrary.utils.ListItemType;
import com.google.gson.Gson;

public class UserV1 extends UserV0 {
    private String email;
    private String phoneNumber;

    @Override
    public int getItemType() {
        return ListItemType.USER_V1;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @NonNull
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
