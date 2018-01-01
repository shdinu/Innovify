package com.innovify.innofyapp;

import android.location.Location;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public abstract class StringUtils {

    public static String PATTERN = "dd/MM/yy-HH:mm:ss:SSS";


    /**
     * This method will convert timeStamp to dd/MM/yy HH:mm:ss:SSS format date object
     * @param date long timestamp
     * @return String formatted date dd/MM/yy-HH:mm:ss:SSS  i.e : 22/04/17 11:48:00:000
     */
    public static String getFormatedDate(long date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PATTERN, Locale.ENGLISH);
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date);
        return simpleDateFormat.format(cal.getTime());
    }

    public static String getDataForFile(long date, Location location, int currentInterval, int nextInterval){
        return getFormatedDate(date)
                +" "+location.getLatitude()
                +" "+location.getLongitude()
                +" "+currentInterval
                +" "+nextInterval;
    }
}
