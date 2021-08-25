package com.geyu.utils;

import com.geyu.base.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class TimeUtil {

    public static Observable<Long>  countDown(int time){
        return Observable.interval(time, TimeUnit.SECONDS)
                .take(time);
    }


    public synchronized static String getRecordDisplayDate(long timeMillis) {

        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        calendar.setTimeInMillis(timeMillis);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);

        if (currentYear > year) {
//            return mYearMonthDayFormat.format(calendar.getTime());
            return String.format(ResUtils.getString(R.string.date_format_y_m_d),year,month,day);
        }

        if (currentMonth > month || currentDay > day) {
            return String.format(ResUtils.getString(R.string.date_format_m_d),month,day);
        }
        return String.format(ResUtils.getString(R.string.date_format_today_time),hour,minute);
    }


    /**
     * 当天开始时间
     * @return
     */
    public static long todayStar(){
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTime().getTime();
    }

    /**
     * 当天结束时间
     * @return
     */
    public static long todayEnd(){
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime().getTime();
    }

    /**
     *
     * @param format 如 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getTime(String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(new Date());
    }
}
