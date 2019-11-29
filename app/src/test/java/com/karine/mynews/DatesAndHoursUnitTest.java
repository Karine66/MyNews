package com.karine.mynews;

import com.karine.mynews.Utils.DatesAndHoursConverter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class DatesAndHoursUnitTest {

    @Test
    public void testDateConvertForSearch() {
        String date = "21/11/2019";
        String actualDate = DatesAndHoursConverter.dateConvertForSearch(date);
        String newDate = "20191121";
        assertEquals(newDate,actualDate );

    }

    @Test
    public void testFormatTime() {

        String actualHour = DatesAndHoursConverter.formatTime(20,18);
        String newHour = "20 heures 18 mn";
        assertEquals(newHour,actualHour );


    }

}