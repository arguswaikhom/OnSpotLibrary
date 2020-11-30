package com.crown.library.onspotlibrary.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import org.jetbrains.annotations.NotNull;

public class OSCommonIntents {

    public static void onIntentTargetOnSpot(Context context, @NotNull String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        intent.setPackage("com.crown.onspot");

        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            onIntentLink(context, url);
        }
    }

    public static void onIntentLink(Context context, @NotNull String url) {
        if (!url.startsWith("http://") && !url.startsWith("https://")) url = "http://" + url;
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }

    public static void onIntentCallRequest(@NotNull Context context, @NotNull String number) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }

    public static void onIntentEmailTo(@NotNull Context context, @NotNull String email) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }

    public static void onIntentShareText(Context context, String text) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, text);
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        context.startActivity(shareIntent);
    }

    public static void onIntentAppOnPlayStore(Context context) {
        final String packageName = context.getPackageName();
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName)));
        } catch (android.content.ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)));
        }
    }

    public static void onIntentAppOnPlayStore(Context context, String packageName) {
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName)));
        } catch (android.content.ActivityNotFoundException e) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)));
        }
    }

    public static void onIntentShareAppLink(Context context) {
        String appLink = "https://play.google.com/store/apps/details?id=" + context.getPackageName();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, appLink);
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        context.startActivity(shareIntent);
    }

    public static void onIntentShareApp(Context context) {
        String text = "getString(R.string.share_msg)" + "\n\n https://play.google.com/store/apps/details?id=" + context.getPackageName();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");

        try {
            // todo: based on different app, get their icon
            /*Uri imageUri = Uri.parse(MediaStore.Images.Media.insertImage(this.getContentResolver(), BitmapFactory.decodeResource(getResources(), R.drawable.logo), null, null));
            intent.putExtra(Intent.EXTRA_STREAM, imageUri);*/
        } catch (Exception e) {
            e.printStackTrace();
        }

        intent.putExtra(Intent.EXTRA_TEXT, text);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        }
    }
}
