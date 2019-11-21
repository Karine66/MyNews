package com.karine.mynews.Utils.Notifications;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.karine.mynews.controllers.activities.NotificationsActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by <Karine> on <DATE-DU-JOUR>.
 */
public class DatesAndHoursConverter {


    private static String alarm;

    /**
     * Convert dates for search (nyt requires)
     *
     * @param dateToFormat
     * @return
     */
    public static String dateConvertForSearch (String dateToFormat) {

        try {
            if (dateToFormat != null && !dateToFormat.isEmpty()) {
                SimpleDateFormat sdf = null;
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateToFormat);
                sdf = new SimpleDateFormat("yyyyMMdd");
                return sdf.format(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * For format Time in date field
     * @param h
     * @param m
     * @return
     */
    public static String formatTime ( int h, int m){

        final String OLD_FORMAT = "HH:mm";
        final String NEW_FORMAT = "HH 'heures' mm 'mn'";

        String oldHourString = h + ":" + m;
        String newHourString = "";

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
            Date d = sdf.parse(oldHourString);
            sdf.applyPattern(NEW_FORMAT);
            newHourString = sdf.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        alarm = newHourString;

        Log.d("TestTime", newHourString);
        return newHourString;
    }

}
