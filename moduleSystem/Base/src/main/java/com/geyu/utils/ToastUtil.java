package com.geyu.utils;

import android.widget.Toast;

import com.geyu.base.BaseApplication;

public class ToastUtil {

    public static void showToast(String msg) {

        Toast.makeText(BaseApplication.getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
