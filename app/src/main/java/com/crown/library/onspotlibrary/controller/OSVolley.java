package com.crown.library.onspotlibrary.controller;


import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Cache;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.nio.charset.StandardCharsets;

public class OSVolley {
    private static final String TAG = OSVolley.class.getName();
    private static volatile OSVolley instance;
    private final Context context;
    private ImageLoader imageLoader;
    private RequestQueue requestQueue;

    private OSVolley(Context context) {
        if (instance != null) {
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }
        this.context = context;
        requestQueue = getRequestQueue();
    }

    public static OSVolley getInstance(Context context) {
        if (instance == null) {
            synchronized (OSVolley.class) {
                if (instance == null) instance = new OSVolley(context);
            }
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        if (!TextUtils.isEmpty(tag)) req.setTag(tag);
        addToRequestQueue(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (requestQueue != null) requestQueue.cancelAll(tag);
    }

    /**
     * Return the cache whether or not the season has expired
     * *Use this for those caches which doesn't have season or for those which you want to display expired data while fetching the latest one
     *
     * @param key: cache key
     * @return: the cached data as string
     */
    public String getCache(String key) {
        Cache.Entry entry = getRequestQueue().getCache().get(key);
        if (entry == null) return null;
        return new String(entry.data, StandardCharsets.UTF_8);
    }

    /**
     * Return the cache which the season hasn't been expired
     * warn: Don't use this method for the cache which doesn't have season; it'll always return null
     *
     * @param key: cache key
     * @return: the cached data as string
     */
    public String getFreshCache(String key) {
        Cache.Entry entry = getRequestQueue().getCache().get(key);
        if (entry == null || entry.isExpired()) return null;
        return new String(entry.data, StandardCharsets.UTF_8);
    }

    public void setCache(String key, String data) {
        setCache(key, data, null);
    }

    /**
     * Save data to cache
     * *Use the url as key for HTTP requests
     *
     * @param key:    key of the cache; use it to get or save the cache
     * @param data:   data to save in the cache
     * @param season: the cache will expire after the season (millisecond)
     */
    public void setCache(String key, String data, Long season) {
        Cache.Entry cacheEntry = new Cache.Entry();
        if (season != null) cacheEntry.ttl = System.currentTimeMillis() + season;
        cacheEntry.data = data.getBytes();
        getRequestQueue().getCache().put(key, cacheEntry);
    }

    public void removeCache(String key) {
        getRequestQueue().getCache().remove(key);
    }

    public boolean isCacheExpired(String key) {
        Cache.Entry entry = getRequestQueue().getCache().get(key);
        return entry == null || entry.isExpired();
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }
}
