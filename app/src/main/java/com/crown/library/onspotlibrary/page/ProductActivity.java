package com.crown.library.onspotlibrary.page;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.crown.library.onspotlibrary.R;
import com.crown.library.onspotlibrary.databinding.ActivityProductBinding;
import com.crown.library.onspotlibrary.model.OSPrice;
import com.crown.library.onspotlibrary.model.business.BusinessV0;
import com.crown.library.onspotlibrary.model.businessItem.BusinessItemV4;
import com.crown.library.onspotlibrary.utils.OSBusinessItemUnitUtils;
import com.crown.library.onspotlibrary.utils.OSCommonIntents;
import com.crown.library.onspotlibrary.utils.OSMessage;
import com.crown.library.onspotlibrary.utils.OSRatingUtils;
import com.crown.library.onspotlibrary.utils.OSString;
import com.crown.library.onspotlibrary.utils.OSUrlUtils;
import com.crown.library.onspotlibrary.utils.emun.BusinessItemPriceUnit;
import com.crown.library.onspotlibrary.utils.emun.BusinessItemStatus;
import com.crown.library.onspotlibrary.views.LoadingBounceDialog;
import com.crown.library.onspotlibrary.views.OSImageSliderAdapter;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class ProductActivity extends AppCompatActivity {
    public static final String PRODUCT_ID = "PRODUCT_ID";

    private String productId;
    private BusinessItemV4 product;
    private BusinessV0 business;
    private final List<String> images = new ArrayList<>();
    private OSImageSliderAdapter adapter;
    private LoadingBounceDialog loadingDialog;
    private ListenerRegistration productListener;
    private ActivityProductBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadingDialog = new LoadingBounceDialog(this);
        handleIntent();
        adapter = new OSImageSliderAdapter(images);
        binding.imageSlider.setSliderAdapter(adapter);
        binding.orderOnlineBtn.setOnClickListener(v -> {
            if (business == null) return;
            String url = getString(R.string.in_app_link_order_online) + "/" + business.getBusinessRefId();
            OSCommonIntents.onIntentTargetOnSpot(this, url);
        });
        binding.offeredByBusiness.setOnClickListener(v -> {
            if (business == null) return;
            String url = getString(R.string.in_app_link_business) + "/" + business.getBusinessRefId();
            OSCommonIntents.onIntentTargetOnSpot(this, url);
        });
    }

    private void handleIntent() {
        // * product-id should be filled by one of these activity call
        // In-app activity call
        productId = getIntent().getStringExtra(PRODUCT_ID);
        if (!TextUtils.isEmpty(productId)) {
            getProductDetails();
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

            // * First path "product", second path "{product-id}"
            // If the url doesn't have 2 paths, this activity won't handle those urls
            if (paths.length == 2) {
                productId = appLinkData.getLastPathSegment();
                getProductDetails();
            } else urlHandleFailed();
        }
    }

    private void urlHandleFailed() {
        // todo: display "can't handle url" screen
        OSMessage.showSBar(this, "Can't handle this url!!");
    }

    private void getProductDetails() {
        loadingDialog.show();
        productListener = FirebaseFirestore.getInstance().collection(OSString.refItem).document(productId).addSnapshotListener((value, error) -> {
            loadingDialog.dismiss();
            if (value == null) {

            } else if (!value.exists()) {

            } else {
                product = value.toObject(BusinessItemV4.class);
                showProduct();
                getBusinessDetails();
            }
        });
    }

    private void getBusinessDetails() {
        FirebaseFirestore.getInstance().collection(OSString.refBusiness).document(product.getBusinessRefId()).get().addOnSuccessListener(doc -> {
            if (doc.exists()) {
                business = doc.toObject(BusinessV0.class);
                assert business != null;
                String offerBy = "offered by <b>" + business.getDisplayName() + "</b>";
                binding.offeredByBusiness.setText(Html.fromHtml(offerBy));
            }
        }).addOnFailureListener(e -> {

        });
    }

    // todo: check for admin-blocked and inactive product
    private void showProduct() {
        if (product == null) return;
        binding.productName.setText(product.getItemName());
        if (product.getStatus() == null || product.getStatus() == BusinessItemStatus.AVAILABLE) {
            binding.statusTv.setBackgroundResource(R.color.item_status_available);
            binding.statusTv.setText(BusinessItemStatus.AVAILABLE.getName());
        } else if (product.getStatus() == BusinessItemStatus.NOT_AVAILABLE) {
            binding.statusTv.setBackgroundResource(R.color.item_status_not_available);
            binding.statusTv.setText(product.getStatus().getName());
        } else if (product.getStatus() == BusinessItemStatus.OUT_OF_STOCK) {
            binding.statusTv.setBackgroundResource(R.color.item_status_out_of_stock);
            binding.statusTv.setText(product.getStatus().getName());
        }

        OSRatingUtils.getReviewInfo(product.getProductRating(), (average, review) -> {
            binding.avgRatingRbar.setRating(Float.parseFloat(average));
            binding.reviewCountTv.setText(review);
        });

        binding.descriptionTv.setText(TextUtils.isEmpty(product.getDescription()) ? "No product description!!" : product.getDescription());
        OSPrice price = product.getPrice();
        BusinessItemPriceUnit unit = price.getUnit() == null ? BusinessItemPriceUnit.item : price.getUnit();
        String quantity = OSBusinessItemUnitUtils.getDisplayText((int) (long) price.getQuantity(), unit);
        String priceDisplay = "Price: <b>" + OSString.inrSymbol + " " + price.getPrice() + "</b> per <b>" + quantity + "</b>";
        binding.priceTv.setText(Html.fromHtml(priceDisplay));
        images.clear();
        images.addAll(product.getImageUrls());
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (productListener != null) productListener.remove();
    }
}