package com.geyu.base;

import android.app.Application;
import android.content.Context;

import com.geyu.database.DaoMaster;
import com.geyu.database.DaoSession;
import com.geyu.database.help.SQLiteOpenHelper;

import androidx.multidex.MultiDex;

public class BaseApplication extends Application {
    private static Context mContext;
    private static DaoSession mDaoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        mContext = this;
        initSql();
    }


    public static Context getContext() {
        return mContext;
    }

    private void initSql() {
        SQLiteOpenHelper openHelper = new SQLiteOpenHelper(this, "accountBook");
        DaoMaster daoMaster = new DaoMaster(openHelper.getWritableDatabase());
        mDaoSession = daoMaster.newSession();
    }

    public static DaoSession getmDaoSession(){
        return mDaoSession;
    }
}
