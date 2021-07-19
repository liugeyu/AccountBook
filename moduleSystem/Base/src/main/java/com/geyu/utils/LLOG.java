package com.geyu.utils;

import android.text.TextUtils;
import android.util.Log;

import com.geyu.base.BuildConfig;

public class LLOG {


    public static String customTagPrefix = "log";
    private static String generateTag() {
        StackTraceElement caller = new Throwable().getStackTrace()[2];
        String tag = "%s.%s(L:%d)";
        String callerClazzName = caller.getClassName();
        callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
        tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber());
        tag = TextUtils.isEmpty(customTagPrefix) ? tag : customTagPrefix + ":" + tag;
        return tag;
    }

    public static void e(String msg) {
        if (BuildConfig.DEBUG) {
            Log.e(generateTag(),msg);
        }
    }
    public static void d(String msg) {
        if (BuildConfig.DEBUG) {
            Log.d(generateTag(),msg);
        }
    }

}
