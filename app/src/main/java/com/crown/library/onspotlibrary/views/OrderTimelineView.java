package com.crown.library.onspotlibrary.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.crown.library.onspotlibrary.R;
import com.crown.library.onspotlibrary.utils.emun.OrderStatus;

import java.util.ArrayList;
import java.util.List;

import static com.crown.library.onspotlibrary.utils.emun.OrderStatus.ACCEPTED;
import static com.crown.library.onspotlibrary.utils.emun.OrderStatus.DELIVERED;
import static com.crown.library.onspotlibrary.utils.emun.OrderStatus.ON_THE_WAY;
import static com.crown.library.onspotlibrary.utils.emun.OrderStatus.ORDERED;
import static com.crown.library.onspotlibrary.utils.emun.OrderStatus.PREPARING;
import static com.crown.library.onspotlibrary.utils.emun.OrderStatus.READY;

public class OrderTimelineView extends LinearLayout {
    private final String[] prePaths = new String[]{ORDERED.name(), ACCEPTED.name(), PREPARING.name(), READY.name(), ON_THE_WAY.name(), DELIVERED.name()};
    private int preTint;
    private int postTint;
    private Context context;
    private boolean showPrePath;
    private List<OrderPassView> views = new ArrayList<>();

    public OrderTimelineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setOrientation(LinearLayout.VERTICAL);
        this.context = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.OrderTimelineView, 0, 0);
        postTint = a.getColor(R.styleable.OrderTimelineView_postTint, context.getColor(android.R.color.holo_blue_light));
        preTint = a.getColor(R.styleable.OrderTimelineView_preTint, context.getColor(android.R.color.darker_gray));
        a.recycle();
    }

    private void refresh() {
        if (views.isEmpty()) return;
        removeAllViews();
        if (showPrePath) {
            List<OrderPassView> list = new ArrayList<>();
            for (String prePath : prePaths) {
                boolean viewFound = false;
                OrderPassView entryView = new OrderPassView(context);
                for (OrderPassView passView : views) {
                    if (passView.getViewId().equals(prePath)) {
                        entryView = passView;
                        viewFound = true;
                        break;
                    }
                }
                if (!viewFound) {
                    entryView.bind(null, null, OrderStatus.valueOf(prePath).getTimelineLabel(), prePath, false);
                }
                list.add(entryView);
            }
            showTimeline(list);
        } else {
            showTimeline(views);
        }
    }

    private void showTimeline(List<OrderPassView> list) {
        for (int i = 0; i < list.size(); i++) {
            OrderPassView view = list.get(i);
            view.setTint(preTint, postTint);
            view.setFirst(i == 0);
            view.setLast(i == list.size() - 1);
            try {
                OrderPassView nextView = list.get(i + 1);
                view.setFollowUpDone(nextView.isDone());
            } catch (IndexOutOfBoundsException ignore) {
            }
            addView(view);
        }
    }

    public void addAll(List<OrderPassView> viewList) {
        this.views = viewList;
        refresh();
    }

    public void add(OrderPassView view) {
        this.views.add(view);
        refresh();
    }

    public void setPreTint(int color) {
        this.preTint = color;
    }

    public void setPostTint(int color) {
        this.postTint = color;
    }

    public void setPrePath(boolean prePath) {
        this.showPrePath = prePath;
    }
}
