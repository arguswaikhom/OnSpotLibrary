package com.crown.library.onspotlibrary.model.review;

import com.crown.library.onspotlibrary.model.ListItem;
import com.crown.library.onspotlibrary.utils.ListItemType;
import com.crown.library.onspotlibrary.utils.emun.OSReviewType;

public class OSReviewV0 extends ListItem {
    private String reviewId;
    private String msg;
    private Double rating;
    private OSReviewType reviewType;

    @Override
    public int getItemType() {
        return ListItemType.OS_REVIEW_V0;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public OSReviewType getReviewType() {
        return reviewType;
    }

    public void setReviewType(OSReviewType reviewType) {
        this.reviewType = reviewType;
    }
}