package com.crown.library.onspotlibrary.views;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.core.widget.ImageViewCompat;

import com.crown.library.onspotlibrary.R;
import com.crown.library.onspotlibrary.databinding.ViewInfoBannerBinding;

public class OSInfoBanner extends FrameLayout {

    private DialogInterface.OnClickListener infoPositiveBtnListener;
    private DialogInterface.OnClickListener infoNegativeBtnListener;

    public OSInfoBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        ViewInfoBannerBinding binding = ViewInfoBannerBinding.inflate(LayoutInflater.from(context), this, true);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.OSInfoBanner, 0, 0);
        String text = typedArray.getString(R.styleable.OSInfoBanner_text);
        boolean hasInfo = typedArray.getBoolean(R.styleable.OSInfoBanner_hasInfo, false);
        int textColor = typedArray.getColor(R.styleable.OSInfoBanner_textColor, -111);
        int bgColor = typedArray.getColor(R.styleable.OSInfoBanner_backgroundColor, -111);
        String infoTitle = typedArray.getString(R.styleable.OSInfoBanner_infoTitle);
        String infoMessage = typedArray.getString(R.styleable.OSInfoBanner_infoMessage);
        int infoTint = typedArray.getColor(R.styleable.OSInfoBanner_infoTint, -111);
        String infoPositiveBtnText = typedArray.getString(R.styleable.OSInfoBanner_infoPositiveBtnText);
        String infoNegativeBtnText = typedArray.getString(R.styleable.OSInfoBanner_infoNegativeBtnText);

        float cornerRadius = typedArray.getDimension(R.styleable.OSInfoBanner_cornerRadius, 32);
        float elevation = typedArray.getDimension(R.styleable.OSInfoBanner_elevation, 8);
        typedArray.recycle();

        binding.textTv.setText(text);
        if (textColor != -111) binding.textTv.setTextColor(textColor);
        if (bgColor != -111) binding.card.setCardBackgroundColor(bgColor);
        binding.card.setRadius(cornerRadius);
        binding.card.setCardElevation(elevation);

        if (hasInfo) {
            if (TextUtils.isEmpty(infoTitle) && TextUtils.isEmpty(infoMessage)) return;
            binding.moreInfoIv.setVisibility(VISIBLE);
            if (infoTint != -111) {
                ImageViewCompat.setImageTintList(binding.moreInfoIv, ColorStateList.valueOf(infoTint));
            }
            binding.moreInfoIv.setOnClickListener(v -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                if (!TextUtils.isEmpty(infoTitle)) builder.setTitle(infoTitle);
                if (!TextUtils.isEmpty(infoMessage)) builder.setMessage(infoMessage);
                if (!TextUtils.isEmpty(infoPositiveBtnText)) {
                    builder.setPositiveButton(infoPositiveBtnText, infoPositiveBtnListener);
                }
                if (!TextUtils.isEmpty(infoNegativeBtnText)) {
                    builder.setNegativeButton(infoNegativeBtnText, infoNegativeBtnListener);
                }
                builder.show();
            });
        }
    }

    public void setInfoPositiveBtnListener(DialogInterface.OnClickListener infoPositiveBtnListener) {
        this.infoPositiveBtnListener = infoPositiveBtnListener;
    }

    public void setInfoNegativeBtnListener(DialogInterface.OnClickListener infoNegativeBtnListener) {
        this.infoNegativeBtnListener = infoNegativeBtnListener;
    }
}
