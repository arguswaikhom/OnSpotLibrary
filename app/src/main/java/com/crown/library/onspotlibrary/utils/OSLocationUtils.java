package com.crown.library.onspotlibrary.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;

import androidx.core.app.ActivityCompat;

import com.crown.library.onspotlibrary.model.OSLocation;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.firestore.GeoPoint;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class OSLocationUtils {
    /**
     * @param start: Start location
     * @param end:   Destination
     * @return : return the distance of the two GeoPoints in KM
     */
    public static double getDistance(GeoPoint start, GeoPoint end) {
        Location startLocation = new Location("start");
        startLocation.setLatitude(start.getLatitude());
        startLocation.setLongitude(start.getLongitude());

        Location endLocation = new Location("end");
        endLocation.setLatitude(end.getLatitude());
        endLocation.setLongitude(end.getLongitude());

        return startLocation.distanceTo(endLocation) / 1000;
    }

    public static double getDistance(OSLocation start, OSLocation end) {
        return getDistance(start.getGeoPoint(), end.getGeoPoint());
    }

    public static String getDistanceLine(OSLocation start, OSLocation end, String suffix) {
        if (start == null || end == null || start.getGeoPoint() == null || end.getGeoPoint() == null)
            return "";
        return String.format(Locale.ENGLISH, "%.2f KM %s", getDistance(start.getGeoPoint(), end.getGeoPoint()), suffix);
    }

    public static String getPostalCode(Context context, Location location) {
        try {
            Geocoder geocoder = new Geocoder(context);
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            return addresses.get(0).getPostalCode();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @deprecated {@link CurrentLocation#get(CurrentLocation.OnReturnLocation, CurrentLocation.OnMissingPermission)}
     */
    @Deprecated
    public static void getCurrentLocation(Context context) {
        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
            if (location != null) {

            }
        });
    }

    public static boolean isEmpty(OSLocation location) {
        return location == null || location.getGeoPoint() == null || location.getAddressLine() == null || location.getHowToReach() == null || location.getAddressLine().trim().equals("") || location.getHowToReach().trim().equals("");
    }

}
