package com.crown.library.onspotlibrary.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

public class NavUtils {

    public static void showOnPlayStore(Activity activity, String packageName) {
        try {
            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName)));
        } catch (android.content.ActivityNotFoundException e) {
            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)));
        }
    }

//    public static void shareApp(Activity activity, String msg, String appLink) {
//        String text = msg + "\n\n" + appLink;
//        Intent intent = new Intent(Intent.ACTION_SEND);
//        intent.setType("*/*");
//
//        try {
//            Uri imageUri = Uri.parse(MediaStore.Images.Media.insertImage(activity.getContentResolver(), BitmapFactory.decodeResource(getResources(), R.drawable.logo), null, null));
//            intent.putExtra(Intent.EXTRA_STREAM, imageUri);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        intent.putExtra(Intent.EXTRA_TEXT, text);
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(intent);
//        }
//    }
}
