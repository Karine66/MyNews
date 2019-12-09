package com.karine.mynews.Utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class DatesAndHoursConverter {

    /**
     * Convert dates for search (nyt requires)
     *
     * @param dateToFormat
     * @return
     */
    public static String dateConvertForSearch(String dateToFormat) {

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
     *
     * @param h
     * @param m
     * @return
     */
    public static String formatTime(int h, int m) {

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
        return newHourString;
    }

    /**
     * Convert dates in dd/MM/yyyy for display
     *
     * @param dateString
     * @return
     */
    public static String dateFormat(String dateString) {
        List<String> strings = Arrays.asList("yyyy-MM-dd", "yyyy-MM-dd'T'HH:mm:ssZ");

        for (String string : strings) {
            try {
                SimpleDateFormat sdf = null;
                Date date = new SimpleDateFormat(string).parse(dateString);
                sdf = new SimpleDateFormat("dd/MM/yyyy");
                return sdf.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
