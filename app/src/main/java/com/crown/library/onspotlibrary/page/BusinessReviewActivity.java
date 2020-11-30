package com.crown.library.onspotlibrary.page;

import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.crown.library.onspotlibrary.R;
import com.crown.library.onspotlibrary.controller.OSPreferences;
import com.crown.library.onspotlibrary.databinding.ActivityBusinessReviewBinding;
import com.crown.library.onspotlibrary.model.business.BusinessV0;
import com.crown.library.onspotlibrary.model.review.OSRUBusinessByCustomer;
import com.crown.library.onspotlibrary.model.user.UserV0;
import com.crown.library.onspotlibrary.utils.OSConfig;
import com.crown.library.onspotlibrary.utils.OSMessage;
import com.crown.library.onspotlibrary.utils.OSString;
import com.crown.library.onspotlibrary.utils.emun.OSPreferenceKey;
import com.crown.library.onspotlibrary.utils.emun.OSReviewType;
import com.crown.library.onspotlibrary.views.LoadingBounceDialog;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.gson.Gson;

import java.util.Date;


public class BusinessReviewActivity extends AppCompatActivity {

    public static final String BUSINESS = "BUSINESS";
    private BusinessV0 business;
    private LoadingBounceDialog loadingDialog;
    private ActivityBusinessReviewBinding binding;
    private OSRUBusinessByCustomer customerReview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBusinessReviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Review requires user to authenticate first
        if (!OSConfig.isAuthenticated(getApplicationContext())) {
            binding.subLoginRequired.getRoot().setVisibility(View.VISIBLE);
            binding.mainContent.setVisibility(View.GONE);
            binding.subLoginRequired.loginBtn.setOnClickListener(this::onClickedLogin);
            return;
        }

        loadingDialog = new LoadingBounceDialog(this);
        handleIntent();
        getRating();
        binding.businessReviewInfoTv.setText(Html.fromHtml(String.format("<b>%s:</b> %s", getString(R.string.header_note), getString(R.string.msg_info_business_review))));
        binding.submitOrderReviewBtn.setOnClickListener(this::onClickedSubmitReview);
    }

    private void getRating() {
        if (business == null) return;
        loadingDialog.show();
        UserV0 user = OSPreferences.getInstance(getApplicationContext()).getObject(OSPreferenceKey.USER, UserV0.class);
        FirebaseFirestore.getInstance().collection(OSString.refReview)
                .whereEqualTo(OSString.fieldCustomer, user.getUserId())
                .whereEqualTo(OSString.fieldBusiness, business.getBusinessRefId())
                .whereEqualTo(OSString.refReviewType, OSReviewType.BUSINESS_BY_CUSTOMER).get()
                .addOnSuccessListener(snapshots -> {
                    loadingDialog.dismiss();
                    if (snapshots != null && !snapshots.isEmpty() && !snapshots.getDocuments().isEmpty()
                            && snapshots.getDocuments().get(0).exists()) {
                        customerReview = snapshots.getDocuments().get(0).toObject(OSRUBusinessByCustomer.class);
                        displayCustomerReview();
                    }
                })
                .addOnFailureListener(e -> {
                    loadingDialog.dismiss();
                    OSMessage.showSBar(this, getString(R.string.msg_failed_to_load));
                    finish();
                });
    }

    private void displayCustomerReview() {
        if (customerReview == null) return;
        binding.businessRbar.setRating((float) (double) customerReview.getRating());
        binding.businessReviewTiet.setText(TextUtils.isEmpty(customerReview.getMsg()) ? "" : customerReview.getMsg());
    }

    private void onClickedSubmitReview(View view) {
        if (business == null) return;
        float rating = binding.businessRbar.getRating();
        String review = binding.businessReviewTiet.getText().toString();

        if (rating <= 0) {
            // Minimum require 1 star
            OSMessage.showSToast(this, "Rating required!!");
            return;
        }

        if (customerReview != null && rating == customerReview.getRating() && review.equals(customerReview.getMsg())) {
            // User didn't change any content, so no need to send request
            OSMessage.showSToast(this, "Review updated!!");
            return;
        }

        if (customerReview == null) {
            UserV0 user = OSPreferences.getInstance(getApplicationContext()).getObject(OSPreferenceKey.USER, UserV0.class);
            customerReview = new OSRUBusinessByCustomer();
            customerReview.setBusiness(business.getBusinessRefId());
            customerReview.setCustomer(user.getUserId());
            customerReview.setCreatedOn(new Timestamp(new Date()));
            customerReview.setReviewId(FirebaseFirestore.getInstance().collection(OSString.refReview).document().getId());
        }

        customerReview.setRating((double) rating);
        customerReview.setMsg(review);
        customerReview.setModifiedOn(new Timestamp(new Date()));

        loadingDialog.show();
        FirebaseFirestore.getInstance().collection(OSString.refReview).document(customerReview.getReviewId())
                .set(customerReview, SetOptions.merge())
                .addOnSuccessListener(v -> {
                    OSMessage.showSToast(this, "Review added");
                    loadingDialog.dismiss();
                })
                .addOnFailureListener(e -> {
                    OSMessage.showSToast(this, "Failed to update review");
                    loadingDialog.dismiss();
                });
    }

    private void onClickedLogin(View view) {
        setResult(RESULT_OK);
        finish();
    }

    private void handleIntent() {
        String json = getIntent().getStringExtra(BUSINESS);
        if (!TextUtils.isEmpty(json)) {
            business = new Gson().fromJson(json, BusinessV0.class);
            displayBusinessDetails();
        }
    }

    private void displayBusinessDetails() {
        binding.businessName.setText(business.getDisplayName());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}