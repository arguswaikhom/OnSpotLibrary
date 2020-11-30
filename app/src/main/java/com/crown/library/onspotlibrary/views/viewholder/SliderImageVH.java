package com.crown.library.onspotlibrary.views.viewholder;

import android.content.Context;
import android.view.View;

import com.bumptech.glide.Glide;
import com.crown.library.onspotlibrary.databinding.LiSliderImageBinding;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class SliderImageVH extends SliderViewAdapter.ViewHolder {
    private final Context context;
    private final LiSliderImageBinding binding;

    public SliderImageVH(View itemView) {
        super(itemView);
        context = itemView.getContext();
        binding = LiSliderImageBinding.bind(itemView);
    }

    public void bind(String url) {
        Glide.with(context).load(url).into(binding.imageIv);
    }
}
