package com.crown.library.onspotlibrary.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.crown.library.onspotlibrary.databinding.ViewCartQuantityBinding;

import java.util.Locale;

public class OSCartQuantityView extends LinearLayout {
    private ViewCartQuantityBinding binding;
    private OnQuantityChangeListener listener;

    public OSCartQuantityView(Context context) {
        super(context);
        init(context, null);
    }

    public OSCartQuantityView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        binding = ViewCartQuantityBinding.inflate(LayoutInflater.from(context), this, true);
        binding.addIv.setOnClickListener(this::onClickedAdd);
        binding.subIv.setOnClickListener(this::onClickedSub);
    }

    private void onClickedAdd(View view) {
        if (listener != null)
            listener.onQuantityChange(this, OnQuantityChangeListener.ADD);
    }

    private void onClickedSub(View view) {
        if (listener != null)
            listener.onQuantityChange(this, OnQuantityChangeListener.SUB);
    }

    public void setQuantity(int quantity) {
        binding.quantityTv.setText(String.format(Locale.ENGLISH, "%d", quantity));
    }

    public void setEnable(boolean enable) {
        binding.addIv.setClickable(enable);
        binding.subIv.setClickable(enable);
    }

    public void setOnQuantityChangeListener(OnQuantityChangeListener listener) {
        this.listener = listener;
    }

    public interface OnQuantityChangeListener {
        int ADD = 1;
        int SUB = 2;

        void onQuantityChange(View view, int mode);
    }
}
