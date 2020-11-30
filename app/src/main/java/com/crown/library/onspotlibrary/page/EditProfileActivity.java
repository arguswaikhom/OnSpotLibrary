package com.crown.library.onspotlibrary.page;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.crown.library.onspotlibrary.R;
import com.crown.library.onspotlibrary.databinding.ActivityEditProfileBinding;
import com.crown.library.onspotlibrary.model.OSLocation;
import com.crown.library.onspotlibrary.model.user.UserV2;
import com.crown.library.onspotlibrary.utils.OSFileUtils;
import com.crown.library.onspotlibrary.utils.OSImageCompression;
import com.crown.library.onspotlibrary.utils.OSImagePicker;
import com.crown.library.onspotlibrary.utils.OSMessage;
import com.crown.library.onspotlibrary.utils.OSString;
import com.crown.library.onspotlibrary.views.LoadingBounceDialog;
import com.crown.library.onspotlibrary.views.OSCreateLocationDialog;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;

import java.io.File;

public class EditProfileActivity extends AppCompatActivity implements OSCreateLocationDialog.OnLocationResponse {

    public static final String USER_ID = "USER_ID";
    private final int RC_VERIFY_PHONE_NO = 100;
    private UserV2 user;
    private LoadingBounceDialog loadingDialog;
    private final boolean hasVerifiedPhoneNo = false;
    private ActivityEditProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.toolbar.setTitle(getString(R.string.title_edit_profile));
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loadingDialog = new LoadingBounceDialog(this);

        String userId = getIntent().getStringExtra(USER_ID);
        if (userId == null) {
            onBackPressed();
            return;
        }
        loadingDialog.show();
        FirebaseFirestore.getInstance().collection(OSString.refUser).document(userId).get().addOnSuccessListener(doc -> {
            loadingDialog.dismiss();
            user = doc.toObject(UserV2.class);
            initUI();
            setUpUI();
        }).addOnFailureListener(e -> {
            loadingDialog.dismiss();
            OSMessage.showSToast(this, getString(R.string.msg_failed_to_load));
            onBackPressed();
        });
    }

    private void initUI() {
        binding.changeProfileBtn.setOnClickListener(v -> new OSImagePicker(this).show());

        binding.changeLocationBtn.setOnClickListener(v -> {
            OSCreateLocationDialog dialog = new OSCreateLocationDialog();
            if (user.getLocation() != null) {
                Bundle bundle = new Bundle();
                bundle.putString(OSCreateLocationDialog.KEY_LOCATION, new Gson().toJson(user.getLocation()));
                dialog.setArguments(bundle);
            }
            dialog.show(getSupportFragmentManager(), "");
        });

        binding.submitProfile.setOnClickListener(v -> {
            String name = binding.nameTiet.getText().toString().trim();
            String number = binding.phoneNoTiet.getText().toString();

            boolean hasEmptyFields = false;
            if (TextUtils.isEmpty(name)) {
                hasEmptyFields = true;
                binding.nameTil.setError(getString(R.string.msg_invalid_input));
            }

            if (TextUtils.isEmpty(number)) {
                hasEmptyFields = true;
                binding.phoneNoTil.setError(getString(R.string.msg_invalid_input));
            }

            if (hasEmptyFields) return;

            // Verify phone number if the user didn't have a phone number before or changed the old number
            //* The phone number will store in the user's object after it is verified
            if (TextUtils.isEmpty(user.getPhoneNumber()) || !user.getPhoneNumber().equals(number)) {
                Intent intent = new Intent(this, PhoneVerificationActivity.class);
                intent.putExtra(PhoneVerificationActivity.PHONE_NO, number);
                startActivityForResult(intent, RC_VERIFY_PHONE_NO);
                return;
            }

            // Update the user document if everything satisfied
            loadingDialog.show();
            FirebaseFirestore.getInstance().collection(OSString.refUser).document(user.getUserId())
                    .update(OSString.fieldDisplayName, name, OSString.fieldPhoneNumber, number, OSString.fieldLocation, user.getLocation())
                    .addOnSuccessListener(result -> {
                        loadingDialog.dismiss();
                        OSMessage.showSToast(this, getString(R.string.msg_update_successful));
                        onBackPressed();
                    })
                    .addOnFailureListener(e -> {
                        loadingDialog.dismiss();
                        OSMessage.showSToast(this, getString(R.string.msg_failed_to_update));
                    });
        });
    }

    private void setUpUI() {
        if (user == null) {
            onBackPressed();
            return;
        }
        Glide.with(this).load(user.getProfileImageUrl()).apply(new RequestOptions().circleCrop()).into(binding.profileImageIv);
        binding.nameTiet.setText(user.getDisplayName());
        binding.phoneNoTiet.setText(user.getPhoneNumber());
        OSLocation location = user.getLocation();
        if (location != null) {
            binding.addressTv.setText(location.getAddressLine());
            binding.addressHowToReachTv.setText(Html.fromHtml("<b>" + getString(R.string.header_how_to_reach) + ":</b>\t" + location.getHowToReach()));
        } else {
            binding.addressTv.setText(getText(R.string.msg_address_not_found));
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case OSImagePicker.RC_SELECT_SINGLE_IMAGES: {
                    try {
                        assert data != null;
                        if (data.getData() == null) {
                            //* Image captured from Camera
                            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                            File image = OSFileUtils.fromBitmap(this, bitmap);
                            OSImageCompression imageCompression = new OSImageCompression(this, Uri.fromFile(image));
                            image = imageCompression.compress();
                            uploadImage(image);
                        } else {
                            //* Image selected from Gallery
                            OSImageCompression imageCompression = new OSImageCompression(this, data.getData());
                            File image = imageCompression.compress();
                            uploadImage(image);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        OSMessage.showSToast(this, getString(R.string.msg_failed_to_read_image));
                    }
                    break;
                }
                case RC_VERIFY_PHONE_NO: {
                    if (data == null) return;
                    String number = data.getStringExtra(PhoneVerificationActivity.PHONE_NO);
                    user.setPhoneNumber(number);
                    binding.submitProfile.performClick();
                    break;
                }
            }
        }
    }

    /**
     * Upload the image to the Firebase bucket
     */
    private void uploadImage(File image) {
        binding.imageUploadsPbar.setVisibility(View.VISIBLE);
        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child(OSString.bucketUserProfile);
        final StorageReference imgStrRef = storageReference.child(user.getUserId());
        UploadTask uploadTask = imgStrRef.putFile(Uri.fromFile(image));
        uploadTask.continueWithTask((Task<UploadTask.TaskSnapshot> task) -> {
            if (task.isSuccessful()) return imgStrRef.getDownloadUrl();
            binding.imageUploadsPbar.setVisibility(View.INVISIBLE);
            OSMessage.showSToast(this, getString(R.string.msg_failed_to_upload));
            throw task.getException();
        }).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Uri downloadUri = task.getResult();
                if (downloadUri == null) {
                    OSMessage.showSToast(this, getString(R.string.msg_failed_to_upload));
                    binding.imageUploadsPbar.setVisibility(View.INVISIBLE);
                    return;
                }
                updateProfileImage(downloadUri);
            } else {
                OSMessage.showSToast(this, getString(R.string.msg_failed_to_upload));
                binding.imageUploadsPbar.setVisibility(View.INVISIBLE);
            }
        });
    }

    /**
     * Update the latest profile image url to the user's document
     */
    private void updateProfileImage(Uri uri) {
        FirebaseFirestore.getInstance().collection(OSString.refUser).document(user.getUserId()).update(OSString.fieldProfileImageUrl, uri.toString()).addOnSuccessListener(result -> {
            Glide.with(this).load(uri).apply(new RequestOptions().circleCrop()).into(binding.profileImageIv);
            OSMessage.showSToast(this, getString(R.string.msg_update_successful));
            binding.imageUploadsPbar.setVisibility(View.INVISIBLE);
        }).addOnFailureListener(e -> {
            OSMessage.showSToast(this, getString(R.string.msg_failed_to_upload));
            binding.imageUploadsPbar.setVisibility(View.INVISIBLE);
        });
    }


    @Override
    public void onLocationResponse(OSLocation location) {
        user.setLocation(location);
        setUpUI();
    }
}