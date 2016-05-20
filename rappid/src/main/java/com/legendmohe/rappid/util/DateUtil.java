package com.legendmohe.rappid.util;

import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by legendmohe on 16/4/28.
 */
public class DateUtil {
    public int weekdayFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public int weekdayToday() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static String getReadableTimestampString() {
        return DateFormat.format("yyyy_MM_dd_hh_mm_ss", new java.util.Date()).toString();
    }
}
