package com.crown.library.onspotlibrary.model;

import com.crown.library.onspotlibrary.utils.ListItemType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UnSupportedContent extends ListItem {
    private String userId;
    private String packageName;
    private final Date happenedAt = new Date();
    private OSBuild build = new OSBuild();
    private List<Object> items = new ArrayList<>();
    private List<String> exceptions = new ArrayList<>();

    public UnSupportedContent() {
    }

    public UnSupportedContent(OSBuild build) {
        this.build = build;
    }

    public UnSupportedContent(int vcApp, String vnApp, String userId, String packageName) {
        build.setVcApp(vcApp);
        build.setVnApp(vnApp);
        this.userId = userId;
        this.packageName = packageName;
    }

    @Override
    public int getItemType() {
        return ListItemType.UNSUPPORTED_CONTENT;
    }

    public List<Object> getItems() {
        return items;
    }

    public void setItems(List<Object> items) {
        this.items = items;
    }

    public void addItem(Object item) {
        this.items.add(item);
    }

    public void addException(String e) {
        this.exceptions.add(e);
    }

    public String getUserId() {
        return userId;
    }

    public Date getHappenedAt() {
        return happenedAt;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public OSBuild getBuild() {
        return build;
    }

    public void setBuild(OSBuild build) {
        this.build = build;
    }

    public List<String> getExceptions() {
        return exceptions;
    }

    public void setExceptions(List<String> exceptions) {
        this.exceptions = exceptions;
    }
}
