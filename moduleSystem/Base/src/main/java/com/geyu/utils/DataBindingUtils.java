package com.geyu.utils;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;

public class DataBindingUtils {



    @BindingAdapter("bindText")
    public static void bindText(TextView tv, int value){
        bindText(tv,""+value);
    }
    @BindingAdapter("bindText")
    public static void bindText(TextView tv, long value){
        bindText(tv,""+value);
    }
    @BindingAdapter("bindText")
    public static void bindText(TextView tv, String value) {
        tv.setText(value);
    }
}
