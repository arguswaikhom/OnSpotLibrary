package com.crown.library.onspotlibrary.controller;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public class OSGlideLoader {

    public static void loadUserProfileImage(Context context, String uid, ImageView imageView) {
        OSImageLoader.getUserProfileImage(context, uid, url -> Glide.with(context).load(url).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                OSVolley.getInstance(context).removeCache(uid);
                OSImageLoader.getUserProfileImage(context, uid, url -> Glide.with(context).load(url).circleCrop().into(imageView), null);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                return false;
            }
        }).circleCrop().into(imageView), null);
    }
}
