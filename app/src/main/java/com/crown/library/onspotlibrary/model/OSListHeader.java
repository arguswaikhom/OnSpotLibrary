package com.crown.library.onspotlibrary.model;


import android.view.Gravity;

import com.crown.library.onspotlibrary.utils.ListItemType;

public class OSListHeader extends ListItem {
    private String header;
    private float textSize = 14;
    private int gravity = Gravity.START;

    public OSListHeader(String header) {
        this.header = header;
    }

    public OSListHeader(String header, int gravity) {
        this.header = header;
        this.gravity = gravity;
    }

    public OSListHeader(String header, float textSize) {
        this.header = header;
        this.textSize = textSize;
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public int getGravity() {
        return gravity;
    }

    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

    @Override
    public int getItemType() {
        return ListItemType.LIST_HEADER;
    }
}
