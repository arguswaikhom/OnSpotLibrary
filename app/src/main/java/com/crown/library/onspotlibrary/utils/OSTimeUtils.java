package com.crown.library.onspotlibrary.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.text.format.DateUtils;

import com.crown.library.onspotlibrary.utils.callback.OSTripleStringResponse;
import com.google.firebase.Timestamp;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class OSTimeUtils {
    public static final String AM = "AM";
    public static final String PM = "PM";

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

    public static Timestamp getTimestampFromJsonObj(String jsonString, String name) {
        Timestamp timestamp = null;
        try {
            JSONObject jsonObject = new JSONObject(jsonString).getJSONObject(name);
            timestamp = new Timestamp(jsonObject.getLong(OSString.fieldTimeStampSecond), jsonObject.getInt(OSString.fieldTimeStampNanosecond));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return timestamp;
    }

    public static boolean isValidTime(String time) {
        if (TextUtils.isEmpty(time) || !time.contains(":")) return false;
        String[] timeArr = time.split(":");
        if (timeArr.length != 2) return false;
        int hr = Integer.parseInt(timeArr[0]);
        int min = Integer.parseInt(timeArr[1]);
        return hr >= 0 && hr <= 24 && min >= 0 && min <= 60;
    }

    /**
     * {@link #isValidTime(String)} should call before calling this method to verify the valid time
     *
     * @param time:     Time in string format. e.g:- "7:49", "23:09"
     * @param response: Extract the hr, min and AM/PM from the time and response
     */
    public static void extractTime(String time, OSTripleStringResponse response) {
        String[] timeArr = time.split(":");
        int hr = Integer.parseInt(timeArr[0]);
        int min = Integer.parseInt(timeArr[1]);

        String zone = hr <= 12 ? AM : PM;
        hr = hr == 0 ? 12 : hr > 12 ? hr % 12 : hr;
        response.onResponse(String.valueOf(hr), min < 10 && String.valueOf(min).length() < 2 ? "0" + min : String.valueOf(min), zone);
    }

    /**
     * {@link #isValidTime(String)} should call before calling this method to verify the valid time
     *
     * @param first:  The first time
     * @param second: The time to be compared with the first time
     * @return: true if first > second. Otherwise false
     */
    public static boolean isFirstBugger(String first, String second) {
        String[] fArr = first.split(":");
        String[] sArr = second.split(":");

        int fHr = Integer.parseInt(fArr[0]);
        int sHr = Integer.parseInt(sArr[0]);

        if (fHr > sHr) return true;
        else if (fHr == sHr) return Integer.parseInt(fArr[1]) > Integer.parseInt(sArr[1]);
        else return false;
    }
}
