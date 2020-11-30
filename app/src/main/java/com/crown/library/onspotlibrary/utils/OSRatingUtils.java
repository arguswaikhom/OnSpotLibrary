package com.crown.library.onspotlibrary.utils;

import com.crown.library.onspotlibrary.model.OSRating;
import com.crown.library.onspotlibrary.utils.callback.OSDoubleStringResponse;

public class OSRatingUtils {

    public static void getReviewInfo(OSRating rating, OSDoubleStringResponse response) {
        double average = rating == null || rating.getAverage() == null ? 0 : rating.getAverage();
        String reviewText = rating == null || rating.getCount() == null || rating.getCount() == 0 ? "0 review" : rating.getCount() + " reviews";
        response.onResponse(String.valueOf(average), reviewText);
    }
}