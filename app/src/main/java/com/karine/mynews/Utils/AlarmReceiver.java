package com.karine.mynews.Utils;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.karine.mynews.controllers.activities.NotificationsActivity;

/**
 * Created by <Karine> on <DATE-DU-JOUR>.
 */
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction() != null && context != null) {
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {

                Log.d("AlarmReceiver", "onReceive:BOOT_COMPLETED");
                LocalData localData = new LocalData(context);
                NotificationScheduler.setReminder(context, AlarmReceiver.class, localData.getHour(), localData.getMin());
                return;
            }
        }
        //Trigger the nofitication
        NotificationScheduler.showNotification(context, NotificationsActivity.class, "You have notification", "Show now ?");

    }
}



