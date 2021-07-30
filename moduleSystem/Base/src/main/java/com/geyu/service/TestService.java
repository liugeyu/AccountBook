package com.geyu.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class TestService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("service","TestService--onCreate");

        Intent ret = new Intent(TestService.this, GeYuJobService.class);
        startService(ret);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("service","TestService-- onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }
}
