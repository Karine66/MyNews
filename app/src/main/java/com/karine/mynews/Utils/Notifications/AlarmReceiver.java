package com.karine.mynews.Utils.Notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.karine.mynews.Utils.NYTStreams;
import com.karine.mynews.controllers.activities.NotificationsActivity;
import com.karine.mynews.models.NYTResultsAPI;
import com.karine.mynews.models.SearchAPI.Search;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by <Karine> on <DATE-DU-JOUR>.
 */
public class AlarmReceiver extends BroadcastReceiver {

    private DisposableObserver<Search> mDisposable;
    private String search;
    private String boxResult;
    private Context context;



    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;

        loadData();
        executeHttpRequestWithRetrofit();
        if (intent.getAction() != null && context != null) {
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {

                Log.d("AlarmReceiver", "onReceive:BOOT_COMPLETED");
                LocalData localData = new LocalData(context);
                NotificationScheduler.setReminder(context, AlarmReceiver.class, localData.getHour(), localData.getMin());
                return;
            }
        }
        //Trigger the nofitication
//       NotificationScheduler.showNotification(context, NotificationsActivity.class, "You have some notifications","show now ?");

    }
    public void loadData() {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        search = preferences.getString("searchNotif", "defaultValueSearchNotif");
        boxResult = preferences.getString("boxNotif", "defaultValuebox");
        Log.d("searchreceiver", search);
        Log.d("TestNotifBox", boxResult);

   }
    private void executeHttpRequestWithRetrofit() {

            this.mDisposable = NYTStreams.streamFetchSearchWithoutDates(search, boxResult)
                    .subscribeWith(new DisposableObserver<Search>() {

                        private int requestNotif;

                        @Override
                        public void onNext(Search response) {
                            NYTResultsAPI nytResultsAPI = NYTResultsAPI.createResultsAPIFromSearchWithoutDates(response);

                            requestNotif = nytResultsAPI.getNYTArticles().size();
             //              NotificationScheduler.showNotification(context,NotificationsActivity.class, "You have"+ nytResultsAPI.getNYTArticles().size()+ " "+"notifications", "show now ?");
                            Log.d("TestOnNextNotif", String.valueOf(nytResultsAPI.getNYTArticles().size()));

                        }

                        @Override
                        public void onComplete() {
                            NotificationScheduler.showNotification(context,NotificationsActivity.class, "You have"+ requestNotif + " "+"notifications", "show now ?");
                            Log.d("ON_CompleteNotif", String.valueOf(requestNotif));
                        }
                        @Override
                        public void onError(Throwable e) {
                            Log.e("onErrorWithoutDates", Log.getStackTraceString(e));
                        }
                    });
        }
    }





