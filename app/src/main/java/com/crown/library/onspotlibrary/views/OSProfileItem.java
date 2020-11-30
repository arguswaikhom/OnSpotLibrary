package com.crown.library.onspotlibrary.views;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.core.widget.ImageViewCompat;

import com.crown.library.onspotlibrary.R;
import com.crown.library.onspotlibrary.databinding.LiProfileItemBinding;

public class OSProfileItem extends LinearLayout {

    private final LiProfileItemBinding binding;

    public OSProfileItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        final int DEFAULT_RES = -111;
        binding = LiProfileItemBinding.inflate(LayoutInflater.from(context), this, true);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.OSProfileItem, 0, 0);
        String title = a.getString(R.styleable.OSProfileItem_title);
        int icon = a.getResourceId(R.styleable.OSProfileItem_icon, DEFAULT_RES);
        int titleColor = a.getColor(R.styleable.OSProfileItem_titleColor, DEFAULT_RES);
        int iconColor = a.getColor(R.styleable.OSProfileItem_iconColor, DEFAULT_RES);
        int dividerColor = a.getColor(R.styleable.OSProfileItem_dividerColor, DEFAULT_RES);

        a.recycle();

        binding.titleTv.setText(title);
        if (icon != DEFAULT_RES) binding.iconIv.setImageResource(icon);
        if (titleColor != DEFAULT_RES) binding.titleTv.setTextColor(titleColor);
        if (iconColor != DEFAULT_RES)
            ImageViewCompat.setImageTintList(binding.iconIv, ColorStateList.valueOf(iconColor));
        if (dividerColor != DEFAULT_RES) binding.verticalDividerV.setBackgroundColor(dividerColor);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        binding.rootLl.setOnClickListener(l);
    }
}
