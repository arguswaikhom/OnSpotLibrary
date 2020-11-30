package com.crown.library.onspotlibrary.utils.emun;

import android.content.Context;

import com.crown.library.onspotlibrary.R;

public enum BusinessRequestStatus {
    PENDING, ACCEPTED, REJECTED, CANCELED;

    public String getButtonText(Context context) {
        switch (this) {
            case PENDING:
                return context.getString(R.string.action_btn_request_sent);
            case ACCEPTED:
                return context.getString(R.string.action_btn_accepted);
            case REJECTED:
            case CANCELED:
            default:
                return context.getString(R.string.action_btn_send_request);
        }

    }
}
