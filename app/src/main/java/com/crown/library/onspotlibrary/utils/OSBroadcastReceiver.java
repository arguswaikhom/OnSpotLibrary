package com.crown.library.onspotlibrary.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.crown.library.onspotlibrary.utils.callback.OnReceiveOSBroadcasts;

public class OSBroadcastReceiver extends BroadcastReceiver {
    private OnReceiveOSBroadcasts receiver;

    public OSBroadcastReceiver() {
    }

    public OSBroadcastReceiver(OnReceiveOSBroadcasts receiver) {
        this.receiver = receiver;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (receiver != null) receiver.onReceiveBroadcast(context, intent);
    }
}
