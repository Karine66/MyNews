package com.karine.mynews.controllers.activities;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import com.karine.mynews.R;
import com.karine.mynews.Utils.Notifications.AlarmReceiver;
import com.karine.mynews.Utils.Notifications.LocalData;
import com.karine.mynews.Utils.Notifications.NotificationScheduler;
import com.karine.mynews.Utils.PopupNotifications;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationsActivity extends AppCompatActivity  {

    LocalData localData;
    int hour, min;
    @BindView(R.id.notifications_toolbar)
    Toolbar mNotifToolbar;
    @BindView(R.id.checkbox_arts)
    CheckBox mBoxArts;
    @BindView(R.id.checkbox_politics)
    CheckBox mBoxPolitics;
    @BindView(R.id.checkbox_business)
    CheckBox mBoxBusiness;
    @BindView(R.id.checkbox_sports)
    CheckBox mBoxSports;
    @BindView(R.id.checkbox_entrepreneurs)
    CheckBox mBoxEntrepreneurs;
    @BindView(R.id.checkbox_travel)
    CheckBox mBoxTravel;
    @BindView(R.id.switch_notif)
    Switch mSwitch;
    @BindView(R.id.input_searchLayout)
    TextInputLayout mInputSearch;
    @BindView(R.id.et_Search)
    EditText mEtSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        ButterKnife.bind(this);
        localData = new LocalData(getApplicationContext());
        hour = localData.getHour();
        min = localData.getMin();
        mSwitch.setChecked((localData.getReminderStatus()));

        this.configureToolbar();
        // this.testCheckBox();
        this.addSwitchButtonListener();
        this.formatTime(hour, min);

        PopupNotifications popupNotifications = new PopupNotifications();
        popupNotifications.show(getSupportFragmentManager(), "example");

    }


        //create toolbar
        private void configureToolbar() {

            //Set the toolbar
            setSupportActionBar(mNotifToolbar);
            //Get a support Action Bar corresponding to this Toolbar
            ActionBar actionBar = getSupportActionBar();
            //Enable the Up button
            assert actionBar != null;
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Notifications");

        }
        // For Switch Button
        public void addSwitchButtonListener () {
            mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    localData.setReminderStatus(isChecked);
                    if (isChecked) {
                        Log.d("TestSwitchOn", "Switch is On");
                        showTimePickerDialog(localData.getHour(), localData.getMin());
                        NotificationScheduler.setReminder(NotificationsActivity.this, AlarmReceiver.class, localData.getHour(), localData.getMin());
                        testCheckBox();
                        if (!validateSearch())
                            return;
                        if (!mBoxTravel.isChecked() && !mBoxPolitics.isChecked() && !mBoxSports.isChecked() &&
                                !mBoxBusiness.isChecked() && !mBoxEntrepreneurs.isChecked() && !mBoxArts.isChecked()) {
                            Toast.makeText(getApplicationContext(), "A least one category must be checked", Toast.LENGTH_SHORT).show();
                            return;
                        } else {
                            Log.d("TestSwitch", "Switch is Off");
                            NotificationScheduler.cancelReminder(NotificationsActivity.this, AlarmReceiver.class);

                        }
                    }
                }
            });
        }

        private void showTimePickerDialog(int h, int m) {
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.time_picker_header, null);

            TimePickerDialog builder  = new TimePickerDialog(this, R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hour, int min) {
                    Log.d("TestHour", "onTimeSet: hour" + hour);
                    Log.d("TestMinute", "onTimeSet: min" +min);

                    Toast.makeText(getApplicationContext(), "Alarm is on :" + hour + "h" + min + "mn",Toast.LENGTH_SHORT).show();
                    localData.setHour(hour);
                    localData.setMin(min);
                   // tvTime.setText(getFormatedTime(hour,min));
                    NotificationScheduler.setReminder(NotificationsActivity.this, AlarmReceiver.class, localData.getHour(), localData.getMin());
                }
            }, h, m, true);
            builder.setCustomTitle(view);
            builder.show();
        }



    //Verify checkbox is checked
    private void testCheckBox() {
        StringBuilder resultBox = new StringBuilder();

        resultBox.append("Arts check :").append(mBoxArts.isChecked());
        resultBox.append("Business check :").append(mBoxBusiness.isChecked());
        resultBox.append("Entrepreneurs check :").append(mBoxEntrepreneurs.isChecked());
        resultBox.append("Politics check :").append(mBoxPolitics.isChecked());
        resultBox.append("Sports check :").append(mBoxSports.isChecked());
        resultBox.append("Travel check :").append(mBoxTravel.isChecked());

        Log.d("TestCheckBoxNotif", resultBox.toString());

    }

    private boolean validateSearch() {

        String searchInput = mInputSearch.getEditText().getText().toString().trim();

        if (searchInput.isEmpty()) {
            mEtSearch.setError("Field can't be empty");
//            Toast.makeText(getApplicationContext(), "Search Field can't be empty", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            mEtSearch.setError(null);
            return true;
        }
    }

    public String formatTime(int h, int m) {

        final String OLD_FORMAT = "HH:mm";
        final String NEW_FORMAT = "HH 'heures' mm 'minutes'";

        String olDateString = h + ":" + m;
        String newDateString = "";

        try{
            SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
            Date d = sdf.parse(olDateString);
            sdf.applyPattern(NEW_FORMAT);
            newDateString = sdf.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.d("TestTime", newDateString);
        return newDateString;
    }

    }

