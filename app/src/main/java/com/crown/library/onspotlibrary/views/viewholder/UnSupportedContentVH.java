package com.crown.library.onspotlibrary.views.viewholder;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.crown.library.onspotlibrary.R;
import com.crown.library.onspotlibrary.databinding.LiUnsupportedContentBinding;
import com.crown.library.onspotlibrary.model.UnSupportedContent;
import com.crown.library.onspotlibrary.utils.NavUtils;
import com.crown.library.onspotlibrary.utils.OSMessage;
import com.crown.library.onspotlibrary.views.LoadingBounceDialog;
import com.google.firebase.firestore.FirebaseFirestore;

public class UnSupportedContentVH extends RecyclerView.ViewHolder {

    private final Activity activity;
    private final LoadingBounceDialog loadingDialog;
    private final LiUnsupportedContentBinding binding;

    public UnSupportedContentVH(@NonNull View itemView) {
        super(itemView);
        this.activity = (Activity) itemView.getContext();
        loadingDialog = new LoadingBounceDialog(activity);
        binding = LiUnsupportedContentBinding.bind(itemView);
        binding.updateAppBtn.setOnClickListener(v -> NavUtils.showOnPlayStore(activity, activity.getPackageName()));
    }

    public void bind(UnSupportedContent content) {
        if (TextUtils.isEmpty(content.getPackageName())) {
            content.setPackageName(activity.getPackageName());
        }
        binding.contactUsBtn.setOnClickListener(v -> {
            loadingDialog.show();
            FirebaseFirestore.getInstance().collection(activity.getString(R.string.ref_report)).add(content).addOnSuccessListener(result -> {
                loadingDialog.dismiss();
                OSMessage.showSToast(activity, "Report submitted");
            }).addOnFailureListener(e -> {
                loadingDialog.dismiss();
                OSMessage.showSToast(activity, "Failed to report");
            });
        });
    }
}
