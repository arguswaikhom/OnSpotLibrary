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

public class OSIllustrationView extends LinearLayout {
    private final ViewIllustrationBinding binding;

    public OSIllustrationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        binding = ViewIllustrationBinding.inflate(LayoutInflater.from(context), this, true);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.OSIllustrationView, 0, 0);
        int illustration = attributes.getResourceId(R.styleable.OSIllustrationView_illustration, OSConfig.DEFAULT_RES);
        String text = attributes.getString(R.styleable.OSIllustrationView_text);
        boolean showButton = attributes.getBoolean(R.styleable.OSIllustrationView_showButton, false);
        String buttonText = attributes.getString(R.styleable.OSIllustrationView_buttonText);
        attributes.recycle();

        setIllustration(illustration);
        setText(text);
        showButton(showButton);
        setButtonText(buttonText);
    }

    public void setIllustration(int res) {
        if (res != OSConfig.DEFAULT_RES) binding.illustrationIv.setImageResource(res);
    }

    public void setText(String text) {
        binding.textTv.setText(text);
    }

    public void setButtonText(String buttonText) {
        if (!TextUtils.isEmpty(buttonText)) binding.btn.setText(buttonText);
    }

    public void showButton(boolean showButton) {
        if (showButton) binding.btn.setVisibility(View.VISIBLE);
        else binding.btn.setVisibility(View.INVISIBLE);
    }

    public void setOnActionClickListener(OnClickListener listener) {
        binding.btn.setOnClickListener(listener);
    }
}
