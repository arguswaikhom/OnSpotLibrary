package com.crown.library.onspotlibrary.views;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.core.widget.ImageViewCompat;

import com.crown.library.onspotlibrary.R;

// todo: use view binding
public class PathSpot extends RelativeLayout {
    private ImageView bgIV;
    private ImageView iconIV;
    private final Context context;

    public PathSpot(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PathSpot, 0, 0);
        int bgTint = a.getColor(R.styleable.PathSpot_bgTint, context.getColor(R.color.colorAccent));
        int iconTint = a.getColor(R.styleable.PathSpot_iconTint, context.getColor(R.color.grey_5));
        int icon = a.getResourceId(R.styleable.PathSpot_src, -1);
        a.recycle();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_path_spot, this, true);

        bgIV = findViewById(R.id.iv_vps_bg);
        iconIV = findViewById(R.id.iv_vps_icon);

        setIcon(icon);
        setBgTint(bgTint);
        setIconTint(iconTint);
    }

    private void setIcon(int icon) {
        if (icon == -1) return;
        iconIV.setImageResource(icon);
    }

    private void setIconTint(int iconTint) {
        ImageViewCompat.setImageTintList(iconIV, ColorStateList.valueOf(iconTint));
    }

    private void setBgTint(int bgTint) {
        ImageViewCompat.setImageTintList(bgIV, ColorStateList.valueOf(bgTint));
    }
}
