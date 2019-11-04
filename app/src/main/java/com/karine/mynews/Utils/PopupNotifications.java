package com.karine.mynews.Utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;

import com.karine.mynews.Utils.Notifications.LocalData;

/**
 * Created by <Karine> on <DATE-DU-JOUR>.
 */
public class PopupNotifications extends AppCompatDialogFragment {

    LocalData localData;
    private String dateNotif;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        loadData();
        builder.setTitle("Notifications Informations")
                .setMessage("Alarm is on for "+ dateNotif)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();

    }

    public void loadData() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        //For dates
       dateNotif = preferences.getString("dateNotif", "defaultdateNotif");
        Log.d("TestDateNotif", dateNotif);
    }
    }

