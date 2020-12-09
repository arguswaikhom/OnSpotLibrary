package com.crown.library.onspotlibrary.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.crown.library.onspotlibrary.R;
import com.crown.library.onspotlibrary.databinding.ViewIllustrationBinding;
import com.crown.library.onspotlibrary.utils.OSConfig;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class OSIllustrationView extends LinearLayout {
    private final ViewIllustrationBinding binding;

    public OSIllustrationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        binding = ViewIllustrationBinding.inflate(LayoutInflater.from(context), this, true);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.OSIllustrationView, 0, 0);
        int illustration = attributes.getResourceId(R.styleable.OSIllustrationView_illustration, OSConfig.DEFAULT_RES);
        int gap = (int) attributes.getDimension(R.styleable.OSIllustrationView_gap, 0);
        String text = attributes.getString(R.styleable.OSIllustrationView_text);
        int textColor = attributes.getColor(R.styleable.OSIllustrationView_textColor, OSConfig.DEFAULT_RES);
        float textSize = attributes.getDimension(R.styleable.OSIllustrationView_textSize, OSConfig.DEFAULT_RES);
        boolean showButton = attributes.getBoolean(R.styleable.OSIllustrationView_showButton, false);
        String buttonText = attributes.getString(R.styleable.OSIllustrationView_buttonText);
        attributes.recycle();

        setIllustration(illustration);
        setText(text);
        setGap(gap);
        if (textSize != OSConfig.DEFAULT_RES) setTextSize(textSize);
        if (textColor != OSConfig.DEFAULT_RES) setTextColor(textColor);
        showButton(showButton);
        setButtonText(buttonText);
    }

    private void setGap(int gap) {
        LayoutParams params = new LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        params.setMargins(0, gap, 0, 0);
        binding.textTv.setLayoutParams(params);
        binding.btn.setLayoutParams(params);
    }

    private void setTextColor(int textColor) {
        binding.textTv.setTextColor(textColor);
    }

    public void setIllustration(int res) {
        if (res != OSConfig.DEFAULT_RES) binding.illustrationIv.setImageResource(res);
    }

    public void setText(String text) {
        binding.textTv.setText(text);
    }

    public void setTextSize(float size) {
        binding.textTv.setTextSize(size);
    }

    public void setButtonText(String buttonText) {
        if (!TextUtils.isEmpty(buttonText)) binding.btn.setText(buttonText);
    }

    public void showButton(boolean showButton) {
        if (showButton) binding.btn.setVisibility(View.VISIBLE);
        else binding.btn.setVisibility(View.GONE);
    }

    public void setOnActionClickListener(OnClickListener listener) {
        binding.btn.setOnClickListener(listener);
    }

    public void show(int resource, String msg) {
        if (getVisibility() != VISIBLE) setVisibility(View.VISIBLE);
        setText(msg);
        setIllustration(resource);
    }

    public void hide() {
        if (getVisibility() == VISIBLE) setVisibility(View.GONE);
    }
}
