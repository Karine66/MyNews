package com.karine.mynews.controllers.activities;

import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationsActivity extends AppCompatActivity {

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
        this.retrieveData();
        this.checkboxChecked();
        this.formatTime(hour, min);
        this.addSwitchButtonListener();
        this.disableSwitchBtn();

    }

    /**
     * Create Toolbar
     */
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

    /**
     * Listener on Switch Button
     */
//    public void addSwitchButtonListener() {
//        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                localData.setReminderStatus(isChecked);
//                saveData();
//                testCheckBox();
//                if (isChecked) {
//                    Log.d("TestSwitchOn", "Switch is On");
//                    showTimePickerDialog(localData.getHour(), localData.getMin());
//                    NotificationScheduler.setReminder(NotificationsActivity.this, AlarmReceiver.class, localData.getHour(), localData.getMin());
//                }
//                if (!validateSearch()) {
//                    mSwitch.setChecked(false);
//                    return;
//                }
//                if (!mBoxTravel.isChecked() && !mBoxPolitics.isChecked() && !mBoxSports.isChecked() &&
//                        !mBoxBusiness.isChecked() && !mBoxEntrepreneurs.isChecked() && !mBoxArts.isChecked()) {
//                        mSwitch.setChecked(false);
//                    Toast.makeText(getApplicationContext(), "A least one category must be checked", Toast.LENGTH_SHORT).show();
//                    return;
//
//                    } else {
//                        Log.d("TestSwitch", "Switch is Off");
//                        NotificationScheduler.cancelReminder(NotificationsActivity.this, AlarmReceiver.class);
//
//                }
//            }
//        });
//    }

    public void addSwitchButtonListener() {
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                saveDataSearch();
                testCheckBox();
                if (isChecked) {
                 if (validateSearch() && (noCheckboxChecked())) {
                     showTimePickerDialog(localData.getHour(), localData.getMin());
                     localData.setReminderStatus(true);
                    NotificationScheduler.setReminder(NotificationsActivity.this, AlarmReceiver.class, localData.getHour(), localData.getMin());
                } else {
                    localData.setReminderStatus(false);
                    NotificationScheduler.cancelReminder(NotificationsActivity.this, AlarmReceiver.class);

                }
            }
        }
    });
    }

    /**
     * For test at least one checkbox is checked
     * @return
     */
    public boolean noCheckboxChecked() {
        if (!mBoxTravel.isChecked() && !mBoxPolitics.isChecked() && !mBoxSports.isChecked() &&
                !mBoxBusiness.isChecked() && !mBoxEntrepreneurs.isChecked() && !mBoxArts.isChecked()) {
            Toast.makeText(getApplicationContext(), "A least one category must be checked", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;

        }
    }
        //for disable switch button for modification
        public void disableSwitchBtn () {
            mEtSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    mSwitch.setChecked(false);

                }

                @Override
                public void afterTextChanged(Editable s) {


                }
            });
        }

        /**
         * Builder for TimePickerDialog
         * @param h
         * @param m
         */
        private void showTimePickerDialog ( int h, int m){
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.time_picker_header, null);

            TimePickerDialog builder = new TimePickerDialog(this, R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hour, int min) {
                    Log.d("TestHour", "onTimeSet: hour" + hour);
                    Log.d("TestMinute", "onTimeSet: min" + min);

                    Toast.makeText(getApplicationContext(), "Alarm is on :" + hour + "h" + min + "mn", Toast.LENGTH_SHORT).show();
                    localData.setHour(hour);
                    localData.setMin(min);
                    formatTime(localData.getHour(), localData.getMin());
                     mAlarmOn.setText(alarm);
                    NotificationScheduler.setReminder(NotificationsActivity.this, AlarmReceiver.class, localData.getHour(), localData.getMin());
                }
            }, h, m, true);
            builder.setCustomTitle(view);
            builder.show();
        }

        /**
         * For save Search Field
         */
        public void saveDataSearch () {

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            Editor edit = preferences.edit();
            edit.putString("searchNotif", mInputSearch.getEditText().getText().toString());
            edit.apply();

            Log.d("TestSearchNotif", mInputSearch.getEditText().getText().toString());
        }

        /**
         * Verify checkbox is checked and save it
         */
        private void testCheckBox () {
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

    /**
     * For checked Checkbox
     */
    public void checkboxChecked() {

            if(boxResult.contains("arts")) {
                mBoxArts.setChecked(true);
            }
            if(boxResult.contains("business")) {
                mBoxBusiness.setChecked(true);
            }
            if(boxResult.contains("entrepreneurs")) {
                mBoxEntrepreneurs.setChecked(true);
            }
            if (boxResult.contains("politics")) {
                mBoxPolitics.setChecked(true);
            }
            if(boxResult.contains("sports")) {
                mBoxSports.setChecked(true);
            }
            if(boxResult.contains("travel")) {
                mBoxTravel.setChecked(true);
            }

        }

        /**
         * For error message in switch button if search field is empty
         * @return
         */
        private boolean validateSearch () {

            String searchInput = mInputSearch.getEditText().getText().toString().trim();

            if (searchInput.isEmpty()) {
                mEtSearch.setError("Field can't be empty");
                return false;
            } else {
                mEtSearch.setError(null);
                return true;
            }
        }

        /**
         * For format Time in date field
         * @param h
         * @param m
         * @return
         */
        public String formatTime ( int h, int m){

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
            alarm = newHourString;

            Log.d("TestTime", newHourString);
            return newHourString;
        }

        /**
         * For retrieve sharePreferences when open notifications page
         */
        public void retrieveData () {
            //For return input Search
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            search = preferences.getString("searchNotif", "defaultValueSearchNotif");
            mEtSearch.setText(search);
            //For return date alarm
            mAlarmOn.setText(alarm);
            //For return checkbox
            boxResult = preferences.getString("boxNotif", "defaultValuebox");

            Log.d("TestSaveSearch", search);

            Log.d("TestBoxNotif", boxResult);
        }
    }




