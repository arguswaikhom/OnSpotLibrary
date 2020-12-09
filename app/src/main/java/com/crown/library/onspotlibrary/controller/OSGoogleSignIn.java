package com.crown.library.onspotlibrary.controller;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.crown.library.onspotlibrary.R;
import com.crown.library.onspotlibrary.utils.CurrentLocation;
import com.crown.library.onspotlibrary.utils.OSCommonDialog;
import com.crown.library.onspotlibrary.utils.OSConfig;
import com.crown.library.onspotlibrary.utils.OSJsonParse;
import com.crown.library.onspotlibrary.utils.OSLaunchUtil;
import com.crown.library.onspotlibrary.utils.OSLocationUtils;
import com.crown.library.onspotlibrary.utils.OSMessage;
import com.crown.library.onspotlibrary.utils.OSString;
import com.crown.library.onspotlibrary.utils.callback.OnBooleanResponse;
import com.crown.library.onspotlibrary.utils.callback.OnFailResponse;
import com.crown.library.onspotlibrary.views.LoadingBounceDialog;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OSGoogleSignIn {
    private final int mRequestCode;
    private boolean isNewUser = false;
    private final String appName;
    private Boolean isAvailableHere = null;
    private final Activity mActivity;
    private final GoogleSignInClient mClient;
    private final FirebaseAuth mAuth;
    private GoogleSignInAccount account;
    private final OnGoogleSignInResponse mCallback;
    private final LoadingBounceDialog loadingBounce;

    public OSGoogleSignIn(Activity activity, GoogleSignInClient client, FirebaseAuth auth, String appName, int requestCode, OnGoogleSignInResponse callback) {
        this.mActivity = activity;
        this.mClient = client;
        this.mAuth = auth;
        this.appName = appName;
        this.mRequestCode = requestCode;
        this.mCallback = callback;
        loadingBounce = new LoadingBounceDialog(activity);
    }

    public OSGoogleSignIn(Activity activity, GoogleSignInClient client, FirebaseAuth auth, int requestCode, OnGoogleSignInResponse callback) {
        this(activity, client, auth, "OnSpot", requestCode, callback);
    }

    /**
     * Launch Google account dialog to select Google account from user's device
     */
    public void pickAccount() {
        Intent signInIntent = mClient.getSignInIntent();
        mActivity.startActivityForResult(signInIntent, mRequestCode);
    }

    /*public void setAccount(Intent data) {
        loadingBounce.show();
        try {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            account = task.getResult(ApiException.class);
        } catch (Exception e) {
            mCallback.onFailureGoogleSignIn("Sign in failed", e);
        }
        loadingBounce.dismiss();
    }*/

    /**
     * Call this method from #onActivityResult() of the activity after the user account was selected
     */
    public void signIn(Intent data) {
        loadingBounce.show();
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            account = task.getResult(ApiException.class);
            assert account != null;
            authToFirebase();
        } catch (Exception e) {
            mCallback.onFailureGoogleSignIn("Sign in failed", e);
        }
        loadingBounce.dismiss();
    }

    /**
     * Authenticate the user's account to the Firebase
     */
    private void authToFirebase() {
        loadingBounce.show();
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(mActivity, task -> {
            loadingBounce.dismiss();
            if (task.isSuccessful()) {
                if (mAuth.getCurrentUser() != null) {
                    if (task.getResult() != null && task.getResult().getAdditionalUserInfo() != null && task.getResult().getAdditionalUserInfo().isNewUser())
                        isNewUser = true;
                    getUserDetails();
                } else mCallback.onFailureGoogleSignIn("Sign in failed", task.getException());
            } else {
                mCallback.onFailureGoogleSignIn("Sign in failed", task.getException());
            }
        });
    }

    /**
     * Know the user is a new user or an existing user after the the user account was created
     *
     * @return: true -> for new users, false -> for already existing user
     */
    public boolean isNewUser() {
        return isNewUser;
    }

    /**
     * This method is called after the sign-in was successful in the {@link #authToFirebase()}
     * It gets all the user's details from the Firebase
     *
     * @onSuccess: {@link OnGoogleSignInResponse#onSuccessGoogleSignIn(DocumentSnapshot)}
     * @onFailure: {@link OnGoogleSignInResponse#onFailureGoogleSignIn(String, Exception)}
     */
    private void getUserDetails() {
        if (mAuth.getUid() == null) mCallback.onFailureGoogleSignIn("Sign in failed", null);

        loadingBounce.show();
        String collection = mActivity.getString(R.string.ref_user);
        FirebaseFirestore.getInstance().collection(collection).document(mAuth.getUid()).get()
                .addOnCompleteListener(doc -> loadingBounce.dismiss())
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists())
                        mCallback.onSuccessGoogleSignIn(documentSnapshot);
                    else mCallback.onFailureGoogleSignIn("Can't get user data.", null);
                })
                .addOnFailureListener(e -> mCallback.onFailureGoogleSignIn("Can't get user data.", e));
    }

    /**
     * Check if the device has internet connection and GPS enabled
     */
    public boolean isDeviceReady() {
        // Check if the device is connected to internet
        Boolean isConnected = OSConfig.hasNetworkConnection(mActivity);
        if (isConnected != null && !isConnected) {
            // Device is not connected to internet
            OSMessage.showAIBar(mActivity, "Please connect your internet and retry", "Retry", v -> checkAppAvailability());
            return false;
        }

        // Check if the device's GPS is enabled
        Boolean isGPSEnabled = OSConfig.isGPSEnabled(mActivity);
        if (isGPSEnabled != null && !isGPSEnabled) {
            OSCommonDialog.requireGPS(mActivity);
            return false;
        }
        return true;
    }

    /**
     * Make sure the app has location permissions to check whether the device is in a launch area or not
     */
    public void checkAppAvailability() {
        // If the location permissions are granted, continue. Or ask for the permission
        if (ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Dexter.withActivity(mActivity).withPermissions(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION).withListener(new MultiplePermissionsListener() {
                @Override
                public void onPermissionsChecked(MultiplePermissionsReport report) {
                    if (report.areAllPermissionsGranted()) onCheckAppAvailability();
                    if (report.isAnyPermissionPermanentlyDenied())
                        OSCommonDialog.appSettings(mActivity);
                }

                @Override
                public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                    token.continuePermissionRequest();
                }
            }).check();
        } else {
            onCheckAppAvailability();
        }
    }

    /**
     * This method is called after all the require location permissions are granted {@link #checkAppAvailability()}
     * This method verifies whether the device is in a launch area or not
     */
    private void onCheckAppAvailability() {
        if (isAvailableHere == null) {
            loadingBounce.show();

            // Get device current location
            CurrentLocation.getInstance(mActivity).get(location -> {

                // Get postal code of the location
                String postalCode = OSLocationUtils.getPostalCode(mActivity, location);
                if (postalCode != null) {

                    // Check if the current location is one of the launch location
                    OSLaunchUtil.isAvailable(mActivity, postalCode, value -> {
                        isAvailableHere = value;
                        loadingBounce.dismiss();

                        if (value) {

                            // Current location is one of a launch location: allow sign in
                            // Do nothing, let the user press the sign-in button
                        } else {

                            // Current location is not one of a launch location
                            OSCommonDialog.appUnavailable(mActivity, appName, (dialog, which) -> {

                                // User said they already has account; let them pick account to verify
                                pickAccount();
                            });
                        }
                    }, ((e, msg) -> {
                        loadingBounce.dismiss();
                        OSMessage.showSToast(mActivity, "Something went wrong!!");
                    }));
                } else loadingBounce.dismiss();
            }, () -> OSCommonDialog.locationError(mActivity), null);
        } else if (!isAvailableHere) {
            OSCommonDialog.appUnavailable(mActivity, appName, (dialog, which) -> pickAccount());
        }
    }

    public Boolean getAvailableHere() {
        return isAvailableHere;
    }

    /**
     * Verify whether the user has an existing OnSpot account or not before sign-in
     *
     * @param successResponse: if the method can execute successfully
     * @param failResponse:    if something wrong happens during the method execution
     * @onSuccess: {@link OnBooleanResponse#onResponse(boolean)}: true -> is the user has an existing account, else false
     * @onFailure: {@link OnFailResponse#onFailed(Exception, String)}
     */
    public void isExistingUser(OnBooleanResponse successResponse, OnFailResponse failResponse) {
        Log.d("debug", "account: " + account);
        if (account != null) {
            StringRequest request = new StringRequest(Request.Method.POST, OSString.apiGetAccountAvailability, response -> {
                JSONObject jsonObject = OSJsonParse.stringToObject(response);
                Boolean available = OSJsonParse.booleanFromObject(jsonObject, "isAvailable");

                if (available == null) {
                    if (failResponse != null) failResponse.onFailed(null, "Something went wrong");
                } else successResponse.onResponse(available);
            }, error -> {
                if (failResponse != null) failResponse.onFailed(error, error.getMessage());
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<>();
                    map.put("email", account.getEmail());
                    return map;
                }
            };

            OSVolley.getInstance(mActivity.getApplicationContext()).addToRequestQueue(request);
        }
    }

    public interface OnGoogleSignInResponse {
        void onSuccessGoogleSignIn(DocumentSnapshot doc);

        void onFailureGoogleSignIn(String response, Exception e);
    }
}
