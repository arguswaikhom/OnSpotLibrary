package com.crown.library.onspotlibrary.utils;

import com.crown.library.onspotlibrary.model.review.OSRItemByCustomer;
import com.crown.library.onspotlibrary.model.review.OSRUItemByCustomer;
import com.crown.library.onspotlibrary.model.review.OSReviewV1;
import com.crown.library.onspotlibrary.utils.callback.OnFailResponse;
import com.crown.library.onspotlibrary.utils.callback.OnVoidResponse;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.Date;

public class OSReviewClassUtils {

    // *Change this method whenever new attribute added or remove from OSRUItemByCustomer or OSRItemByCustomer
    public static OSRUItemByCustomer getUploadItemByCustomerReview(OSRItemByCustomer review) {
        OSRUItemByCustomer uploadReview = new OSRUItemByCustomer();
        uploadReview.setBusiness(review.getBusiness().getBusinessRefId());
        uploadReview.setCustomer(review.getCustomer().getUserId());
        uploadReview.setItem(review.getItem().getItemId());
        uploadReview.setCreatedOn(review.getCreatedOn());
        uploadReview.setModifiedOn(review.getModifiedOn());
        uploadReview.setReviewId(review.getReviewId());
        uploadReview.setReviewType(review.getReviewType());
        uploadReview.setMsg(review.getMsg());
        uploadReview.setRating(review.getRating());
        return uploadReview;
    }

    public static <T extends OSReviewV1> void updateReview(T review, OnVoidResponse success, OnFailResponse failure) {
        String reviewId = review.getReviewId() == null ? FirebaseFirestore.getInstance().collection(OSString.refReview).document().getId() : review.getReviewId();
        if (review.getReviewId() == null) {
            // 1* User is giving review for the first time
            // 2* Needs to update to the review object not to satisfy this "if" condition again
            // if the user tries to update the review content just after they created (while the user is in the same page)
            review.setReviewId(reviewId);
            review.setCreatedOn(new Timestamp(new Date()));
        }

        review.setModifiedOn(new Timestamp(new Date()));
        FirebaseFirestore.getInstance().collection(OSString.refReview)
                .document(reviewId).set(review, SetOptions.merge())
                .addOnSuccessListener(result -> success.onVoidResponse())
                .addOnFailureListener(e -> failure.onFailed(e, e.getMessage()));
    }
}
