package com.crown.library.onspotlibrary.model;

import android.os.Build;

// todo: fix version code and name
public class OSBuild {
    final String model = Build.MODEL;
    final String brand = Build.BRAND;
    final String device = Build.DEVICE;
    final String product = Build.PRODUCT;
    final int sdk = Build.VERSION.SDK_INT;
    final String hardware = Build.HARDWARE;
    final String os = Build.VERSION.BASE_OS;
    final String manufacturer = Build.MANUFACTURER;
    int vcApp;
    String vnApp;
   /* final int vcLibrary = BuildConfig.VERSION_CODE;
    final String vnLibrary = BuildConfig.VERSION_NAME;*/

    public OSBuild() {
    }

    public OSBuild(int vcApp, String vnApp) {
        this.vcApp = vcApp;
        this.vnApp = vnApp;
    }

    public int getVcApp() {
        return vcApp;
    }

    public void setVcApp(int vcApp) {
        this.vcApp = vcApp;
    }

    public String getVnApp() {
        return vnApp;
    }

    public void setVnApp(String vnApp) {
        this.vnApp = vnApp;
    }

    public int getSdk() {
        return sdk;
    }

    public String getDevice() {
        return device;
    }

    public String getModel() {
        return model;
    }

    public String getProduct() {
        return product;
    }

    /*public int getVcLibrary() {
        return vcLibrary;
    }

    public String getVnLibrary() {
        return vnLibrary;
    }*/

    public String getBrand() {
        return brand;
    }

    public String getHardware() {
        return hardware;
    }

    public String getOs() {
        return os;
    }

    public String getManufacturer() {
        return manufacturer;
    }
}
