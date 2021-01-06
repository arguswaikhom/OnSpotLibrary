package com.crown.library.onspotlibrary.views;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TableLayout;

import com.crown.library.onspotlibrary.databinding.ViewOrderItemBinding;

import java.util.Locale;

public class OrderItemView extends TableLayout {
    private final Context context;
    private boolean hasDarkTheme;

    public OrderItemView(Context context) {
        super(context);
        this.context = context;
    }

    public OrderItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    /**
     * @deprecated
     */
    public void addChild(int quantity, String title, double price) {
        addChild(String.format(Locale.ENGLISH, "x%d", quantity), title, String.format(Locale.ENGLISH, "₹ %.2f", price));
    }

    public void addChild(int quantity, String title, int price) {
        addChild(String.format(Locale.ENGLISH, "x%d", quantity), title, String.format(Locale.ENGLISH, "₹ %d", price));
    }

    public void addChild(String title, int value) {
        addChild("", title, String.format(Locale.ENGLISH, "₹ %d", value));
    }

    public void clear() {
        this.removeAllViews();
    }

    public void addChild(String a, String b, String c) {
        ViewOrderItemBinding binding = ViewOrderItemBinding.inflate(LayoutInflater.from(context), this, false);
        binding.quantityTv.setText(a);
        binding.nameTv.setText(b);
        binding.priceTv.setText(c);

        if (hasDarkTheme) {
            binding.quantityTv.setTextColor(Color.WHITE);
            binding.nameTv.setTextColor(Color.WHITE);
            binding.priceTv.setTextColor(Color.WHITE);
        }

        addView(binding.getRoot());
    }

    public void setHasDarkTheme(boolean hasDarkTheme) {
        this.hasDarkTheme = hasDarkTheme;
    }
}
