package com.geyu.utils;

public class ErrHandler {

    public static String getErrMsg(Throwable throwable) {
        return throwable.getMessage();
    }
}
