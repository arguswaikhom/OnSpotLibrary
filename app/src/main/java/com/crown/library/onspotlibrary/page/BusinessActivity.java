package com.crown.library.onspotlibrary.page;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.crown.library.onspotlibrary.R;
import com.crown.library.onspotlibrary.databinding.ActivityBusinessBinding;
import com.crown.library.onspotlibrary.model.OSLocation;
import com.crown.library.onspotlibrary.model.business.BusinessV4;
import com.crown.library.onspotlibrary.utils.OSCommonIntents;
import com.crown.library.onspotlibrary.utils.OSConfig;
import com.crown.library.onspotlibrary.utils.OSMessage;
import com.crown.library.onspotlibrary.utils.OSRatingUtils;
import com.crown.library.onspotlibrary.utils.OSString;
import com.crown.library.onspotlibrary.utils.OSUrlUtils;
import com.crown.library.onspotlibrary.views.LoadingBounceDialog;
import com.crown.library.onspotlibrary.views.OSImageSliderAdapter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class BusinessActivity extends AppCompatActivity {
    public static final String BUSINESS_ID = "BUSINESS_ID";

    private BusinessV4 business;
    private String businessRefId;
    private final List<String> images = new ArrayList<>();
    private OSImageSliderAdapter adapter;
    private ActivityBusinessBinding binding;
    private LoadingBounceDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBusinessBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadingDialog = new LoadingBounceDialog(this);
        adapter = new OSImageSliderAdapter(images);
        binding.imageSlider.setSliderAdapter(adapter);
        setUpOnClickListeners();
        handleIntent();
    }

    private void setUpOnClickListeners() {
        binding.reviewLl.setOnClickListener(v -> {
            if (business == null) return;
            if (OSConfig.isAuthenticated(getApplicationContext())) {
                Intent intent = new Intent(this, BusinessReviewActivity.class);
                intent.putExtra(BusinessReviewActivity.BUSINESS, new Gson().toJson(business));
                startActivity(intent);
            } else {
                OSMessage.showSBar(this, "Login to give review.");
            }
        });

        binding.contactChip.setOnClickListener(v -> OSCommonIntents.onIntentCallRequest(this, business.getMobileNumber()));
        binding.emailChip.setOnClickListener(v -> OSCommonIntents.onIntentEmailTo(this, business.getEmail()));
        binding.siteChip.setOnClickListener(v -> OSCommonIntents.onIntentLink(this, business.getWebsite()));

        binding.orderOnlineBtn.setOnClickListener(v -> {
            if (business == null) return;
            String url = getString(R.string.in_app_link_order_online) + "/" + business.getBusinessRefId();
            OSCommonIntents.onIntentTargetOnSpot(this, url);
        });
    }


    private void handleIntent() {
        // * business-id should be filled by one of these activity call
        // In-app activity call
        businessRefId = getIntent().getStringExtra(BUSINESS_ID);
        if (!TextUtils.isEmpty(businessRefId)) {
            getBusinessDetails();
            return;
        }

        // App link activity call
        try {
            handleAppLinkIntent();
        } catch (MalformedURLException e) {
            // This exception will never occur
            e.printStackTrace();
            urlHandleFailed();
        }
    }

    private void handleAppLinkIntent() throws MalformedURLException {
        String appLinkAction = getIntent().getAction();
        Uri appLinkData = getIntent().getData();

        if (Intent.ACTION_VIEW.equals(appLinkAction) && appLinkData != null) {
            String[] paths = OSUrlUtils.getPaths(appLinkData);

            // * First path "business", second path "{business-id}"
            // If the url doesn't have 2 paths, this activity won't handle those urls
            if (paths.length == 2) {
                businessRefId = appLinkData.getLastPathSegment();
                getBusinessDetails();
            } else urlHandleFailed();
        }
    }

    private void urlHandleFailed() {
        // todo: display "can't handle url" screen
        OSMessage.showSBar(this, "Can't handle this url!!");
    }

    private void getBusinessDetails() {
        loadingDialog.show();
        FirebaseFirestore.getInstance().collection(OSString.refBusiness).document(businessRefId).get()
                .addOnSuccessListener(doc -> {
                    loadingDialog.dismiss();
                    // todo: check for business visibility

                    business = doc.toObject(BusinessV4.class);

                    Log.d("debug", "Business: " + business);
                    displayBusinessDetails();
                })
                .addOnFailureListener(e -> {
                    loadingDialog.dismiss();
                    OSMessage.showSBar(this, getString(R.string.msg_failed_to_load));
                });
    }

    private void displayBusinessDetails() {
        if (business == null) {
            OSMessage.showSBar(this, getString(R.string.msg_failed_to_load));
            return;
        }

        images.clear();
        images.addAll(business.getImageUrls());
        adapter.notifyDataSetChanged();

        binding.businessName.setText(business.getDisplayName());
        boolean hodAvailability = business.getHodAvailable() == null || !business.getHodAvailable() ? false : business.getHodAvailable();
        binding.hodAvailabilityBtn.setText(hodAvailability ? getString(R.string.msg_hod_available) : getString(R.string.msg_hod_not_available));

        OSRatingUtils.getReviewInfo(business.getBusinessRating(), (average, review) -> {
            binding.avgRatingRbar.setRating(Float.parseFloat(average));
            binding.reviewCountTv.setText(review);
        });

        if (!TextUtils.isEmpty(business.getMobileNumber()))
            binding.contactChip.setVisibility(View.VISIBLE);
        if (!TextUtils.isEmpty(business.getEmail())) binding.emailChip.setVisibility(View.VISIBLE);
        if (!TextUtils.isEmpty(business.getWebsite())) binding.siteChip.setVisibility(View.VISIBLE);

        OSLocation location = business.getLocation();
        binding.addressTv.setText(Html.fromHtml(String.format("<b>Address:</b> %s", location.getAddressLine())));
        binding.howToReachTv.setText(Html.fromHtml(String.format("<b>How to reach:</b> %s", TextUtils.isEmpty(location.getHowToReach()) ? "..." : location.getHowToReach())));
    }
}