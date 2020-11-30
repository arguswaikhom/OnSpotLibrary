package com.crown.library.onspotlibrary.model.preference;

import com.crown.library.onspotlibrary.utils.emun.OSAPOnSpotHomeHODFilter;

public class OSOnSpotAppPreferences extends OSAppPreferences {
    private OSAPOnSpotHomeHODFilter homeHODFilter = OSAPOnSpotHomeHODFilter.ONLY_HOD_BUSINESS;

    public OSAPOnSpotHomeHODFilter getHomeHODFilter() {
        return homeHODFilter;
    }

    public void setHomeHODFilter(OSAPOnSpotHomeHODFilter homeHODFilter) {
        this.homeHODFilter = homeHODFilter;
    }
}
