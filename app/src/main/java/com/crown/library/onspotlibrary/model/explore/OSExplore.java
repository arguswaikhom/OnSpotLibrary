package com.crown.library.onspotlibrary.model.explore;

import com.crown.library.onspotlibrary.model.ListItem;
import com.crown.library.onspotlibrary.utils.ListItemType;
import com.crown.library.onspotlibrary.utils.emun.OSClass;
import com.google.gson.Gson;

/**
 * !Do not upload {@link OSExplore} class object to the server with "osClass"
 * *Representation of "osClass" in {@link OSExplore} class and other classes are different
 * *Checkout documentation in {@link OSClass} for more details
 */
public class OSExplore extends ListItem {
    private OSClass osClass;
    private String id;
    private String imageUrl;

    public OSExplore() {

    }

    public static OSExplore fromJson(String json) {
        return new Gson().fromJson(json, OSExplore.class);
    }

    public OSClass getOsClass() {
        return osClass;
    }

    public void setOsClass(OSClass osClass) {
        this.osClass = osClass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public int getItemType() {
        return ListItemType.EXPLORE;
    }
}
