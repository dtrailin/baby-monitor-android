package com.example.mathew.baby_monitor;

import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * Created by matthewkuo on 2016-02-28.
 */
public class TokenRefreshListenerService extends InstanceIDListenerService {
    @Override
    public void onTokenRefresh() {
        Intent intent = new Intent(this, RegistrationService.class);
        startService(intent);
    }
}
