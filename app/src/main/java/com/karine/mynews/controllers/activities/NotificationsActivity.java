package com.karine.mynews.controllers.activities;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

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
    @BindView(R.id.alarm_on)
    EditText mAlarmOn;
    private String search;
    private String alarm;
    private String boxResult;
    private boolean box;


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
//       this.disableSwitchBtn();
        //For return input Search
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        search = preferences.getString("searchNotif", "defaultValueSearchNotif");
        mEtSearch.setText(search);
        alarm = preferences.getString("dateNotif", "defaultValueAlarm");
        mAlarmOn.setText(alarm);
        boxResult = preferences.getString("boxNotif", "defaultValuebox");

        Log.d("TestSaveSearch", search);
        Log.d("TestHourAlarm", alarm);
        Log.d("TestBoxNotif", boxResult);

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
                        saveData();
                        testCheckBox();
                        showTimePickerDialog(localData.getHour(), localData.getMin());
                        NotificationScheduler.setReminder(NotificationsActivity.this, AlarmReceiver.class, localData.getHour(), localData.getMin());
                    }
                        if (!validateSearch()) {
                            return;
                        }
                        if (!mBoxTravel.isChecked() && !mBoxPolitics.isChecked() && !mBoxSports.isChecked() &&
                                !mBoxBusiness.isChecked() && !mBoxEntrepreneurs.isChecked() && !mBoxArts.isChecked()) {
                            Toast.makeText(getApplicationContext(), "A least one category must be checked", Toast.LENGTH_SHORT).show();
                            return;

                        } else {
                            Log.d("TestSwitch", "Switch is Off");
                            NotificationScheduler.cancelReminder(NotificationsActivity.this, AlarmReceiver.class);

                        }
                    }

            });
        }
        //for disable switch button for modification
//    public void disableSwitchBtn() {
//        mEtSearch.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                mSwitch.setEnabled(true);
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                mSwitch.setEnabled(true);
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
////                mSwitch.setEnabled(true);
//
//            }
//        });
//    }


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
        //For save search
        public void saveData() {

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            Editor edit = preferences.edit();
            edit.putString("searchNotif", mInputSearch.getEditText().getText().toString());
            edit.apply();

            Log.d("TestSearchNotif", mInputSearch.getEditText().getText().toString());
        }



    //Verify checkbox is checked
    private void testCheckBox() {
        StringBuilder resultBox = new StringBuilder();

        if (mBoxArts.isChecked()) {
            resultBox.append("arts").append(" ");

        }
        if (mBoxBusiness.isChecked()) {
            resultBox.append("business").append(" ");

        }
        if (mBoxEntrepreneurs.isChecked()) {
            resultBox.append("entrepreneurs").append(" ");

        }
        if (mBoxPolitics.isChecked()) {
            resultBox.append("politics").append(" ");

        }
        if (mBoxSports.isChecked()) {
            resultBox.append("sports").append(" ");

        }
        if (mBoxTravel.isChecked()) {
            resultBox.append("travel").append(" ");

        }
        Log.d("TestCheckBox", resultBox.toString());

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Editor edit = preferences.edit();
        edit.putString("boxNotif", resultBox.toString());

        edit.apply();
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
        final String NEW_FORMAT = "HH 'heures' mm 'mn'";

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
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString("dateNotif",newDateString );
        edit.apply();
        Log.d("TestTime", newDateString);
        return newDateString;
    }



    }

