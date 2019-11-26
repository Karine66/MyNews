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

import java.text.SimpleDateFormat;
import java.util.Date;

import io.reactivex.observers.DisposableObserver;

public class AlarmReceiver extends BroadcastReceiver {

    private DisposableObserver<Search> mDisposable;
    private String search;
    private String boxResult;
    private Context context;
    private String dateBegin;
    private String dateEnd;

    /**
     * Handling the broadcast
     *
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;

        loadData();
        executeHttpRequestWithRetrofit();
        datesForNotif();

        if (intent.getAction() != null && context != null) {
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {

                LocalData localData = new LocalData(context);
                NotificationScheduler.setReminder(context, AlarmReceiver.class, localData.getHour(), localData.getMin());
                return;
            }
        }
    }

    /**
     * Retrieve data for httpRequest
     */
    public void loadData() {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        search = preferences.getString("searchNotif", "defaultValueSearchNotif");
        boxResult = preferences.getString("boxNotif", "defaultValuebox");
        dateBegin = preferences.getString("dateBegin", "defaultdateBeginNotif");
        dateEnd = preferences.getString("dateEnd", "defaultValuedateEndNotif");
        Log.d("searchreceiver", search);
        Log.d("TestNotifBox", boxResult);

    }

    /**
     * HTTP request RXJAVA
     */
    private void executeHttpRequestWithRetrofit() {
        //create suscriber
        this.mDisposable = NYTStreams.streamFetchSearch(search, boxResult, dateBegin, dateEnd)
                .subscribeWith(new DisposableObserver<Search>() {

                    private int requestNotif;

                    @Override
                    public void onNext(Search response) {
                        NYTResultsAPI nytResultsAPI = NYTResultsAPI.createResultsAPIFromSearch(response);
                        requestNotif = nytResultsAPI.getNYTArticles().size();
                        Log.d("TestOnNextNotif", String.valueOf(nytResultsAPI.getNYTArticles().size()));
                    }

                    @Override
                    public void onComplete() {
                        NotificationScheduler.showNotification(context, NotificationsActivity.class, "You have" + " " + requestNotif + " " + "notifications", "Show now ?");
                        Log.d("ON_CompleteNotif", String.valueOf(requestNotif));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("onErrorNotif", Log.getStackTraceString(e));
                    }
                });
    }

    /**
     * dates for search in notifications from yesterday to today (in format requires by NYT)
     */
    public void datesForNotif() {

        Date yesterday = new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24));
        Date today = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String dateBegin = dateFormat.format(yesterday);
        String dateEnd = dateFormat.format(today);
        Log.d("TestdateBeginForNotif", dateBegin);
        Log.d("TestdateEndForNotif", dateEnd);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString("dateBegin", dateBegin);
        edit.putString("dateEnd", dateEnd);
        edit.apply();
    }
}





