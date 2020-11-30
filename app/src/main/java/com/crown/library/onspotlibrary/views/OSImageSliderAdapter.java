package com.crown.library.onspotlibrary.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.crown.library.onspotlibrary.R;
import com.crown.library.onspotlibrary.views.viewholder.SliderImageVH;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class OSImageSliderAdapter extends SliderViewAdapter<SliderImageVH> {

    private final List<String> imageUrls;

    public OSImageSliderAdapter(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    @Override
    public SliderImageVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.li_slider_image, null);
        return new SliderImageVH(inflate);
    }


    @Override
    public void onBindViewHolder(SliderImageVH viewHolder, int position) {
        viewHolder.bind(imageUrls.get(position));
    }


    @Override
    public int getCount() {
        return imageUrls.size();
    }
}
