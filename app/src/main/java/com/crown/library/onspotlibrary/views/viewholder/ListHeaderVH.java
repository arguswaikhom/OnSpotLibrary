package com.crown.library.onspotlibrary.views.viewholder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.crown.library.onspotlibrary.databinding.LiListHeaderBinding;
import com.crown.library.onspotlibrary.model.OSListHeader;

public class ListHeaderVH extends RecyclerView.ViewHolder {
    private final LiListHeaderBinding binding;

    public ListHeaderVH(@NonNull View itemView) {
        super(itemView);
        binding = LiListHeaderBinding.bind(itemView);
    }

    public void bind(OSListHeader header) {
        binding.headerTextTv.setText(header.getHeader());
        binding.headerTextTv.setGravity(header.getGravity());
        binding.headerTextTv.setTextSize(header.getTextSize());
    }
}
