package com.crown.library.onspotlibrary.utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;

import androidx.core.app.ActivityCompat;

import com.crown.library.onspotlibrary.utils.callback.OnVoidResponse;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

public class CurrentLocation {
    private static CurrentLocation instance;
    private Context context;
    private Location location;

    public interface OnReturnLocation {
        void onReturnLocation(Location location);
    }

    public interface OnMissingPermission {
        void onMissingPermission();
    }

    public CurrentLocation(Context context) {
        this.context = context;
    }

    public static CurrentLocation getInstance(Context context) {
        if (instance == null) return new CurrentLocation(context);
        return instance;
    }

    @Deprecated
    public void get(OnReturnLocation onReturnLocation, OnMissingPermission onMissingPermission) {
        if (this.location != null) {
            onReturnLocation.onReturnLocation(location);
            return;
        }

        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (onMissingPermission != null) onMissingPermission.onMissingPermission();
            return;
        }

        fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
            if (location != null) {
                this.location = location;
                onReturnLocation.onReturnLocation(location);
            }
        });
    }

    public void get(OnReturnLocation onReturnLocation, OnVoidResponse failResponse, OnMissingPermission onMissingPermission) {
        if (this.location != null) {
            onReturnLocation.onReturnLocation(location);
            return;
        }

        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (onMissingPermission != null) onMissingPermission.onMissingPermission();
            return;
        }

        fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
            if (location != null) {
                this.location = location;
                onReturnLocation.onReturnLocation(location);
            } else {
                if (failResponse != null) failResponse.onVoidResponse();
            }
        });
    }
}
