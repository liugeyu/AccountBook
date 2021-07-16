package com.geyu.utils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class TimeUtil {

    public static Observable<Long>  countDown(int time){
        return Observable.interval(time, TimeUnit.SECONDS)
                .take(time);
    }
}
