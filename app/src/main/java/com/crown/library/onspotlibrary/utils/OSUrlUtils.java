package com.crown.library.onspotlibrary.utils;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URL;

public class OSUrlUtils {

    public static String[] getPaths(String url) throws MalformedURLException {
        return getPaths(new URL(url));
    }

    public static String[] getPaths(Uri url) throws MalformedURLException {
        return getPaths(url.toString());
    }

    public static String[] getPaths(URL url) {
        String paths = url.getPath().replaceFirst("/", "");
        return paths.split("/");
    }
}
