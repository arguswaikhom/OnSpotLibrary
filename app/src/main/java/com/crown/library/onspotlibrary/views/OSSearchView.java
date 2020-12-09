package com.crown.library.onspotlibrary.views;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.crown.library.onspotlibrary.R;
import com.crown.library.onspotlibrary.databinding.ViewSearchBinding;
import com.crown.library.onspotlibrary.utils.OSConfig;
import com.crown.library.onspotlibrary.utils.callback.OnStringResponse;

public class OSSearchView extends LinearLayout {

    private ViewSearchBinding binding;
    private OnStringResponse stringResponse;

    public OSSearchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        binding = ViewSearchBinding.inflate(LayoutInflater.from(context), this, true);

        final int defaultColorValue = -111;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.OSSearchView, 0, 0);
        String hintText = typedArray.getString(R.styleable.OSSearchView_hintText);
        int hintTextColor = typedArray.getColor(R.styleable.OSSearchView_hintTextColor, defaultColorValue);
        int backgroundColor = typedArray.getColor(R.styleable.OSSearchView_backgroundColor, defaultColorValue);
        boolean showSearchIcon = typedArray.getBoolean(R.styleable.OSSearchView_showSearchIcon, false);
        boolean showSearchButton = typedArray.getBoolean(R.styleable.OSSearchView_showSearchButton, false);
        typedArray.recycle();
        if (backgroundColor != defaultColorValue) {
            binding.card.setCardBackgroundColor(backgroundColor);
        }
        if (hintTextColor != defaultColorValue) {
            binding.etVsInput.setHintTextColor(hintTextColor);
            binding.etVsInput.setTextColor(hintTextColor);
            binding.loadingPbar.setIndeterminateTintList(ColorStateList.valueOf(hintTextColor));
        }
        setHint(hintText);
        if (showSearchIcon) binding.searchIconIv.setVisibility(VISIBLE);
        if (showSearchButton) binding.searchIbtn.setVisibility(VISIBLE);
        binding.searchIbtn.setOnClickListener(this::onClickedSearch);
        binding.etVsInput.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                binding.searchIbtn.performClick();
                return true;
            }
            return false;
        });
    }

    private void onClickedSearch(View view) {
        String keywords = binding.etVsInput.getText().toString();
        if (TextUtils.isEmpty(keywords)) OSConfig.requestKeyboard(binding.etVsInput);
        if (stringResponse != null) stringResponse.onResponse(keywords);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void hideKeyboard() {
        OSConfig.hideKeyboard((Activity) getContext());
    }

    public void setHint(String hint) {
        binding.etVsInput.setHint(hint);
    }

    public EditText getField() {
        return binding.etVsInput;
    }

    public void setOnSearchClickedListener(OnStringResponse listener) {
        this.stringResponse = listener;
    }

    public void showProcessing() {
        if (binding.loadingPbar.getVisibility() != VISIBLE)
            binding.loadingPbar.setVisibility(View.VISIBLE);
    }

    public void dismissProcessing() {
        if (binding.loadingPbar.getVisibility() == VISIBLE)
            binding.loadingPbar.setVisibility(View.GONE);

    }
}
