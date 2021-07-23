package com.geyu.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.geyu.base.BaseActivity;

import androidx.fragment.app.FragmentActivity;

public class ToActivity {

    public static void toActivity(FragmentActivity activity, Class<? extends BaseActivity> clazz){
        activity.startActivity(new Intent(activity,clazz));
    }

}
