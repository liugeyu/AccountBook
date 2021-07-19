package com.geyu.utils;

import android.app.Activity;

import com.geyu.base.BaseActivity;

import java.util.Stack;

public class ScreenManager {

    private static volatile ScreenManager instance;
    private Stack<BaseActivity> activities;

    private ScreenManager(){}

    public static ScreenManager getInstance() {
        if (instance == null) {
            synchronized (ScreenManager.class) {
                if (instance == null) {
                    instance = new ScreenManager();
                }
            }
        }
        return instance;
    }

    public void add(BaseActivity activity) {
        if (activities == null) activities = new Stack<>();
        activities.add(activity);
    }

    public void remove(BaseActivity activity) {
        activities.remove(activity);
    }

    /**
     * 退出
     */
    public void exit() {
        for (int i = 0; i < activities.size(); i++) {
            Activity a = activities.get(i);
            if (a != null && !a.isFinishing()) {
                a.finish();
            }
        }
    }

    /**
     * 获取最后第二个界面
     * @return
     */
    public Activity getPreActivity() {
        if (activities.size() >= 2) {
            return activities.get(activities.size() - 2);
        }
        return null;
    }
}
