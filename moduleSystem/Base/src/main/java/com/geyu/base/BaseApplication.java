package com.geyu.base;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

public class BaseApplication extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        mContext = this;
    }


    public static Context getContext() {
        return mContext;
    }
}
