package com.crown.library.onspotlibrary.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.crown.library.onspotlibrary.R;

import java.util.Locale;

public class OrderItemView extends TableLayout {

    private Context context;

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

    public void clear() {
        this.removeAllViews();
    }

    public void addChild(String a, String b, String c) {
        LinearLayout root = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.view_order_item, null);
        ((TextView) root.findViewById(R.id.tv_voi_quantity)).setText(a);
        ((TextView) root.findViewById(R.id.tv_voi_item_name)).setText(b);
        ((TextView) root.findViewById(R.id.tv_voi_price)).setText(c);
        addView(root);
    }
}
