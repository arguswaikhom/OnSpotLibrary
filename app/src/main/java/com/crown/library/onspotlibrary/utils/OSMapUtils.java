package com.crown.library.onspotlibrary.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;

import com.crown.library.onspotlibrary.R;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.firestore.GeoPoint;

import java.util.ArrayList;
import java.util.List;

public class OSMapUtils {

    public static void showLocation(Activity activity, GeoPoint geoPoint, String label) {
        showLocation(activity, new LatLng(geoPoint.getLatitude(), geoPoint.getLongitude()), label);
    }

    public static void showLocation(Activity activity, LatLng latLng, String label) {
        Uri uri;
        if (label == null || label.trim().equals(""))
            uri = Uri.parse("geo:0,0?q=" + latLng.latitude + "," + latLng.longitude);
        else
            uri = Uri.parse("geo:0,0?q=" + latLng.latitude + "," + latLng.longitude + "(" + label + ")");

        openMap(activity, uri);
    }

    public static void showDirection(Activity activity, GeoPoint... geoPoints) {
        List<LatLng> latLngs = new ArrayList<>();
        for (GeoPoint g : geoPoints) {
            latLngs.add(new LatLng(g.getLatitude(), g.getLongitude()));
        }
        showDirection(activity, latLngs.toArray(new LatLng[latLngs.size()]));
    }

    public static void showDirection(Activity activity, LatLng... latLngs) {
        StringBuilder uriBuilder = new StringBuilder("https://www.google.com/maps/dir//");
        for (LatLng latLng : latLngs) {
            uriBuilder.append(latLng.latitude);
            uriBuilder.append(",");
            uriBuilder.append(latLng.longitude);
            uriBuilder.append("/");
        }
        openMap(activity, Uri.parse(uriBuilder.toString()));
    }

    private static void openMap(Activity activity, Uri uri) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage(activity.getString(R.string.map_package));
            activity.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            activity.startActivity(new Intent(Intent.ACTION_VIEW, uri));
        }
    }
}
