package com.crown.library.onspotlibrary.model.review;

import com.crown.library.onspotlibrary.utils.ListItemType;
import com.google.firebase.Timestamp;

public class OSReviewV1 extends OSReviewV0 {
    private Timestamp createdOn;
    private Timestamp modifiedOn;

    public OSReviewV1() {
    }

    @Override
    public int getItemType() {
        return ListItemType.OS_REVIEW_V1;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public Timestamp getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Timestamp modifiedOn) {
        this.modifiedOn = modifiedOn;
    }
}
