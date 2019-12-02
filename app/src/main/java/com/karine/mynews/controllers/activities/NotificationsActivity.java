package com.karine.mynews.controllers.activities;

import android.app.TimePickerDialog;
import android.content.Context;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.karine.mynews.R;
import com.karine.mynews.Utils.Notifications.AlarmReceiver;
import com.karine.mynews.Utils.Notifications.LocalData;
import com.karine.mynews.Utils.Notifications.NotificationScheduler;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.karine.mynews.Utils.DatesAndHoursConverter.formatTime;


public class NotificationsActivity extends AppCompatActivity {


    public String alarm;
    public String date;
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
    @BindView(R.id.notifications)
    RelativeLayout mRelativeLayout;
    private String search;
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
        formatTime(hour, min);
        alarm = formatTime(hour, min);
        this.retrieveData();
        this.checkboxChecked();
        this.addSwitchButtonListener();
        this.disableSwitchBtn();
        this.hideKeyboard();
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
    public void addSwitchButtonListener() {
        mSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            saveDataSearch();
            testCheckBox();

            if ((isChecked) && (validateSearch()) && (noCheckboxChecked())) {
                mSwitch.setChecked(true);
                showTimePickerDialog(localData.getHour(), localData.getMin());
                localData.setReminderStatus(true);
                NotificationScheduler.setReminder(NotificationsActivity.this, AlarmReceiver.class, localData.getHour(), localData.getMin());

            } else {
                mSwitch.setChecked(false);
                localData.setReminderStatus(false);
                NotificationScheduler.cancelReminder(NotificationsActivity.this, AlarmReceiver.class);
            }
        });
    }

    /**
     * For test at least one checkbox is checked
     *
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

    /**
     * for disable switch button for modifications
     */
    public void disableSwitchBtn() {
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
     *
     * @param h
     * @param m
     */
    public void showTimePickerDialog(int h, int m) {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.time_picker_header, null);

        TimePickerDialog builder = new TimePickerDialog(this, R.style.DialogTheme, (view1, hour, min) -> {
            Log.d("TestHour", "onTimeSet: hour" + hour);
            Log.d("TestMinute", "onTimeSet: min" + min);

            localData.setHour(hour);
            localData.setMin(min);
            alarm = formatTime(localData.getHour(), localData.getMin());
            mAlarmOn.setText(alarm);

            NotificationScheduler.setReminder(NotificationsActivity.this, AlarmReceiver.class, localData.getHour(), localData.getMin());
        }, h, m, true);
        builder.setCustomTitle(view);
        builder.show();
    }

    /**
     * For save Search Field
     */
    public void saveDataSearch() {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Editor edit = preferences.edit();
        edit.putString("searchNotif", mInputSearch.getEditText().getText().toString());
        edit.apply();

        Log.d("TestSearchNotif", mInputSearch.getEditText().getText().toString());
    }

    /**
     * Verify checkbox is checked and save it
     */
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

    /**
     * For checked Checkbox
     */
    public void checkboxChecked() {

        if (boxResult.contains("arts")) {
            mBoxArts.setChecked(true);
        }
        if (boxResult.contains("business")) {
            mBoxBusiness.setChecked(true);
        }
        if (boxResult.contains("entrepreneurs")) {
            mBoxEntrepreneurs.setChecked(true);
        }
        if (boxResult.contains("politics")) {
            mBoxPolitics.setChecked(true);
        }
        if (boxResult.contains("sports")) {
            mBoxSports.setChecked(true);
        }
        if (boxResult.contains("travel")) {
            mBoxTravel.setChecked(true);
        }

    }

    /**
     * For error message in switch button if search field is empty
     *
     * @return
     */
    private boolean validateSearch() {

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
     * For retrieve sharePreferences when open notifications page
     */
    public void retrieveData() {
        //For return input Search
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        search = preferences.getString("searchNotif", "defaultValueNotif");
        mEtSearch.setText(search);
        //For return date alarm
        mAlarmOn.setText(alarm);
        //For return checkbox
        boxResult = preferences.getString("boxNotif", "defaultValuebox");

        Log.d("TestSaveSearch", search);

        Log.d("TestBoxNotif", boxResult);
    }

    /**
     * Hide keyboard on click in layout
     */
    public void hideKeyboard() {
        mRelativeLayout.setOnClickListener(v -> {
            InputMethodManager inputMethodManager = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
        });
    }
}




