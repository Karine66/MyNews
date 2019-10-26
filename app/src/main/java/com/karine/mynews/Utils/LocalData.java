package com.karine.mynews.Utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by <Karine> on <DATE-DU-JOUR>.
 */
public class LocalData {

    private static  String APP_SHARED_PREFS = "notifPref";

    private SharedPreferences appSharedPrefs;
    private SharedPreferences.Editor prefsEditor;

    private static final String reminderStatus = "reminderStatus";
    private static final String hour ="hour";
    private static final String min = "min";

    public LocalData(Context context) {

        this.appSharedPrefs = context.getSharedPreferences(APP_SHARED_PREFS, context.MODE_PRIVATE);
        this.prefsEditor = appSharedPrefs.edit();
    }
    //Settings Page set reminder

    public boolean getReminderStatus() {
        return appSharedPrefs.getBoolean(reminderStatus, false);
    }

    public void setReminderStatus (boolean status) {
        prefsEditor.putBoolean(reminderStatus, status);
        prefsEditor.apply();
    }
    //Settings Page reminder Time (Hour)
    public int get_hour() {
        return appSharedPrefs.getInt(hour, 20);
    }
    public void set_hour(int h) {
        prefsEditor.putInt(hour,h);
        prefsEditor.apply();
    }
    //Settings Page Reminder Time (minutes)
    public int get_min() {
        return appSharedPrefs.getInt(min,0);
    }
    public void set_min (int m) {
        prefsEditor.putInt(min, m);
        prefsEditor.apply();
    }
    public void reset () {
        prefsEditor.clear();
        prefsEditor.apply();
    }

}
