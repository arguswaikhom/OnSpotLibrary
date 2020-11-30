package com.crown.library.onspotlibrary.views;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.core.widget.ImageViewCompat;

import com.crown.library.onspotlibrary.databinding.ViewOrderPassBinding;

public class OrderPassView extends LinearLayout {
    private int preTint;
    private int postTint;
    private String viewId;
    private boolean isLast;
    private boolean isFirst;
    private boolean isDone;
    private boolean followUpDone;
    private ViewOrderPassBinding binding;

    public OrderPassView(Context context) {
        super(context);
        inti(context, null);
    }

    public OrderPassView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inti(context, attrs);
    }

    private void inti(Context context, AttributeSet attrs) {
        binding = ViewOrderPassBinding.inflate(LayoutInflater.from(context), this, true);
        preTint = context.getColor(android.R.color.darker_gray);
        postTint = context.getColor(android.R.color.holo_blue_light);
    }

    /**
     * Set whether this checkpoint is going to be the last checkpoint in the timeline or not
     *
     * @param last: true is yes, otherwise false. By default it's false
     */
    public void setLast(boolean last) {
        isLast = last;
        setPath();
    }

    /**
     * Set whether this checkpoint is going to be the first checkpoint in the timeline or not
     *
     * @param first: true, is yes, otherwise false. By default it's false
     */
    public void setFirst(boolean first) {
        isFirst = first;
        setPath();
    }

    /**
     * For the first checkpoint, the top entry path will be hidden and
     * for the last checkpoint the bottom path will be hidden
     */
    private void setPath() {
        if (isFirst) binding.pathTopIv.setVisibility(View.INVISIBLE);
        else binding.pathTopIv.setVisibility(View.VISIBLE);
        if (isLast) binding.pathBottomIv.setVisibility(View.INVISIBLE);
        else binding.pathBottomIv.setVisibility(View.VISIBLE);
    }

    /**
     * Update proper color indication of the path;
     * if the checkpoint is passed (done), the top and mid-checkpoint will be filled
     * if the follow up (next) checkpoint is also passed (done), bottom path will also be filled
     * otherwise left everything with {preTint} color
     */
    private void updatePath() {
        if (isDone) {
            binding.pathTopIv.setBackgroundColor(postTint);
            if (followUpDone) binding.pathBottomIv.setBackgroundColor(postTint);
            else binding.pathBottomIv.setBackgroundColor(preTint);
            ImageViewCompat.setImageTintList(binding.checkpointIv, ColorStateList.valueOf(postTint));
        } else {
            binding.pathTopIv.setBackgroundColor(preTint);
            binding.pathBottomIv.setBackgroundColor(preTint);
            ImageViewCompat.setImageTintList(binding.checkpointIv, ColorStateList.valueOf(preTint));
        }
    }

    public void bind(String day, String time, String label, String viewId, boolean hasDone) {
        this.viewId = viewId;
        this.isDone = hasDone;

        binding.dayTv.setText(day == null ? "..." : day);
        binding.timeTv.setText(time == null ? "..." : time);
        binding.labelTv.setText(label);
        updatePath();
    }

    /**
     * Get the tint colors and update the UI
     *
     * @param preTint:  used for paths and checkpoints which has to pass
     * @param postTint: used for paths and checkpoints which already passed (done)
     */
    public void setTint(int preTint, int postTint) {
        this.preTint = preTint;
        this.postTint = postTint;
        updatePath();
    }

    public String getViewId() {
        return viewId;
    }

    public void setViewId(String viewId) {
        this.viewId = viewId;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        this.isDone = done;
        updatePath();
    }

    public void setFollowUpDone(boolean followUpDone) {
        this.followUpDone = followUpDone;
        updatePath();
    }
}
