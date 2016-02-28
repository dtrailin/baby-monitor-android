package com.example.mathew.baby_monitor;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by matthewkuo on 2016-02-28.
 */
public class NotificationsListenerService extends GcmListenerService {

    @Override
    public void onMessageReceived(String from, Bundle data) {
        String message = data.getString("message");
        Log.d("MESSAGE RECEIVED", "From: " + from);
        Log.d("MESSAGE RECEIVED", "Message: " + message);

    }
}
