package com.crown.library.onspotlibrary.page;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.crown.library.onspotlibrary.databinding.ActivityContactUsBinding;
import com.crown.library.onspotlibrary.utils.OSString;

public class ContactUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityContactUsBinding binding = ActivityContactUsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.contactInstagramTv.setText(String.format("@%s", OSString.contactInstagram));
        binding.contactGmailTv.setText(OSString.contactGmail);

        binding.instagramLl.setOnClickListener(this::onClickedInstagram);
        binding.onGmailLl.setOnClickListener(this::onClickedGmail);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    void onClickedInstagram(View view) {
        Uri uri = Uri.parse("https://www.instagram.com/" + OSString.contactInstagram + "/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.instagram.android");

        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/" + OSString.contactInstagram + "/")));
        }
    }

    void onClickedGmail(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{OSString.contactGmail});
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}