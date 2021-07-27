package com.geyu.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.geyu.base.BaseActivity;

import java.io.Serializable;

import androidx.fragment.app.FragmentActivity;

public class ToActivity {

    public static final String serializabkeKey = "serializabkeKey";
    public static void toActivity(FragmentActivity activity, Class<? extends BaseActivity> clazz){
        activity.startActivity(new Intent(activity,clazz));
    }


    public static void toActivity(FragmentActivity activity, Class<? extends BaseActivity> clazz, Serializable serializable) {
        Intent intent = new Intent(activity,clazz);
        intent.putExtra(serializabkeKey,serializable);
        activity.startActivity(intent);
    }

}
