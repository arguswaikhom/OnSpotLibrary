package com.crown.library.onspotlibrary.utils;

import android.annotation.SuppressLint;
import android.text.format.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class OSTimeUtils {
    public static String getTimeAgo(long second) {
        return DateUtils.getRelativeTimeSpanString(
                TimeUnit.SECONDS.toMillis(second),
                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
    }

    public static String getDay(long second) {
        Date date = new Date(second * 1000);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int year = calendar.get(Calendar.YEAR);
        @SuppressLint("SimpleDateFormat") String month = new SimpleDateFormat("MMM").format(calendar.getTime());

        return String.format("%s %s %s", day, month, year);
    }

    public static String getTime(long second) {
        Date date = new Date(second * 1000);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour >= 12) hour = hour % 12;
        int min = calendar.get(Calendar.MINUTE);

        String zone;
        if (calendar.get(Calendar.AM_PM) == Calendar.AM) {
            zone = "am";
        } else {
            zone = "pm";
        }
        return String.format("%s:%s %s", hour == 0 ? 12 : hour, min, zone);
    }
}
