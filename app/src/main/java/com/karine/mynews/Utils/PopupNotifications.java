package com.karine.mynews.Utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;

import com.karine.mynews.Utils.Notifications.LocalData;

/**
 * Created by <Karine> on <DATE-DU-JOUR>.
 */
public class PopupNotifications extends AppCompatDialogFragment {

    LocalData localData;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Notifications Informations")
                .setMessage("Alarm is on for "+localData)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();

    }
    }

