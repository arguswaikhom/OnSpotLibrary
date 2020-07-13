package com.crown.library.onspotlibrary.controller;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.crown.library.onspotlibrary.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class OSGoogleSignIn {
    private int mRequestCode;
    private Activity mActivity;
    private GoogleSignInClient mClient;
    private FirebaseAuth mAuth;
    private OnGoogleSignInResponse mCallback;

    public interface OnGoogleSignInResponse {
        void onSuccessGoogleSignIn(DocumentSnapshot doc);

        void onFailureGoogleSignIn(String response, Exception e);
    }

    public OSGoogleSignIn(Activity activity, GoogleSignInClient client, FirebaseAuth auth, int requestCode, OnGoogleSignInResponse callback) {
        this.mActivity = activity;
        this.mClient = client;
        this.mAuth = auth;
        this.mRequestCode = requestCode;
        this.mCallback = callback;
    }

    public void pickAccount() {
        Intent signInIntent = mClient.getSignInIntent();
        mActivity.startActivityForResult(signInIntent, mRequestCode);
    }

    public void signIn(Intent data) {
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            assert account != null;
            authToFirebase(account);
        } catch (Exception e) {
            mCallback.onFailureGoogleSignIn("Sign in failed", e);
        }
    }

    private void authToFirebase(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(mActivity, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    if (mAuth.getCurrentUser() != null) getUserDetails();
                    else mCallback.onFailureGoogleSignIn("Sign in failed", task.getException());
                } else {
                    mCallback.onFailureGoogleSignIn("Sign in failed", task.getException());
                }
            }
        });
    }

    private void getUserDetails() {
        if (mAuth.getUid() == null) mCallback.onFailureGoogleSignIn("Sign in failed", null);

        String collection = mActivity.getString(R.string.ref_user);
        FirebaseFirestore.getInstance().collection(collection).document(mAuth.getUid()).get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) mCallback.onSuccessGoogleSignIn(documentSnapshot);
                    else mCallback.onFailureGoogleSignIn("Can't get user data.", null);
                })
                .addOnFailureListener(e -> mCallback.onFailureGoogleSignIn("Can't get user data.", e));
    }
}
