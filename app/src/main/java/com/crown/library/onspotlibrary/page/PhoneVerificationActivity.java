package com.crown.library.onspotlibrary.page;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.crown.library.onspotlibrary.R;
import com.crown.library.onspotlibrary.controller.OSViewAnimation;
import com.crown.library.onspotlibrary.databinding.ActivityPhoneVerificationV2Binding;
import com.crown.library.onspotlibrary.utils.OSMessage;
import com.crown.library.onspotlibrary.utils.OSValidate;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken;
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks;

import java.util.concurrent.TimeUnit;

public class PhoneVerificationActivity extends AppCompatActivity {
    public static final String PHONE_NO = "PHONE_NO";
    public static final String TAG = PhoneVerificationActivity.class.getName();

    private String phoneNumber;
    private String verificationID;
    private boolean hasSentCodeBefore = false;
    private ForceResendingToken resendingToken;
    private ActivityPhoneVerificationV2Binding binding;
    private final OnVerificationStateChangedCallbacks phoneVerificationCallBack = new OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            /*binding.otpTiet.setText(phoneAuthCredential.getSmsCode());
            signInWithPhoneAuthCredential(phoneAuthCredential);*/
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            if (e instanceof FirebaseAuthInvalidCredentialsException) {
                OSMessage.showSToast(getApplicationContext(), getString(R.string.msg_invalid_request));
            } else if (e instanceof FirebaseTooManyRequestsException) {
                OSMessage.showSToast(getApplicationContext(), getString(R.string.msg_server_error));
            } else {
                OSMessage.showSToast(getApplicationContext(), getString(R.string.msg_something_went_wrong));
            }
            showSendOtp();
        }

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationID = s;
            resendingToken = forceResendingToken;
            OSMessage.showSToast(getApplicationContext(), getString(R.string.msg_otp_sent));
            showVerify();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPhoneVerificationV2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        phoneNumber = getIntent().getStringExtra(PHONE_NO);
        if (!TextUtils.isEmpty(phoneNumber))
            binding.phoneNoTiet.setText(phoneNumber.replace("+91", ""));
        binding.sendOtpBtn.setOnClickListener(this::onClickedSendOTP);
        binding.verifyBtn.setOnClickListener(this::onClickedVerify);
    }

    private void onClickedSendOTP(View view) {
        phoneNumber = "+91" + binding.phoneNoTiet.getText().toString();

        if (!OSValidate.isPhoneNumber(phoneNumber)) {
            binding.otpTil.setError(getString(R.string.msg_invalid_input));
            return;
        }

        if (hasSentCodeBefore) {
            PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNumber, 120, TimeUnit.SECONDS, this, phoneVerificationCallBack, resendingToken);
        } else {
            hasSentCodeBefore = true;
            binding.sendOtpBtn.setText(getString(R.string.action_btn_resend_otp));
            PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNumber, 120, TimeUnit.SECONDS, this, phoneVerificationCallBack);
        }
    }

    private void onClickedVerify(View view) {
        phoneNumber = "+91" + binding.phoneNoTiet.getText().toString();
        String otp = binding.otpTiet.getText().toString();

        if (!OSValidate.isPhoneNumber(phoneNumber)) {
            binding.otpTil.setError(getString(R.string.msg_invalid_input));
            showSendOtp();
            return;
        }

        if (TextUtils.isEmpty(otp)) {
            binding.otpTil.setError(getString(R.string.msg_invalid_input));
            return;
        }

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationID, otp);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth.getInstance().signInWithCredential(credential).addOnSuccessListener(result -> {
            OSMessage.showSToast(this, getString(R.string.msg_verified));
            onVerifiedMobileNumber();
        }).addOnFailureListener(e -> {
            e.printStackTrace();
            binding.otpTiet.setError(getString(R.string.msg_invalid_code));
        });
    }

    private void onVerifiedMobileNumber() {
        Intent intent = new Intent();
        intent.putExtra(PHONE_NO, phoneNumber.replace("+91", ""));
        setResult(RESULT_OK, intent);
        finish();
    }

    private void showSendOtp() {
        OSViewAnimation.collapse(binding.verifyRoot);
        OSViewAnimation.expand(binding.sendOtpRoot);
    }

    private void showVerify() {
        OSViewAnimation.collapse(binding.sendOtpRoot);
        OSViewAnimation.expand(binding.verifyRoot);
    }
}
