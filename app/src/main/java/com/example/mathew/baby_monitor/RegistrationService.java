package com.example.mathew.baby_monitor;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

/**
 * Created by matthewkuo on 2016-02-28.
 */
public class RegistrationService extends IntentService {
    public RegistrationService() {
        super("RegistrationService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        InstanceID myId = InstanceID.getInstance(this);
        String registrationToken = null;
        try {
            registrationToken = myId.getToken(
                    getString(R.string.gcm_defaultSenderId),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE,
                    null
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("Registration Token", registrationToken);

        GcmPubSub subscription = GcmPubSub.getInstance(this);
        try {
            subscription.subscribe(registrationToken, "/topics/cry", null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
