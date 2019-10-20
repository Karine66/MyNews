package com.karine.mynews.controllers.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.CheckBox;

import com.karine.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationsActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        ButterKnife.bind(this);
        this.configureToolbar();
        this.testCheckBox();
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

    private void testCheckBox() {
        StringBuilder resultBox = new StringBuilder();

        resultBox.append("Arts check :").append(mBoxArts.isChecked());
        resultBox.append("Business check :").append(mBoxBusiness.isChecked());
        resultBox.append("Entrepreneurs check :").append(mBoxEntrepreneurs.isChecked());
        resultBox.append("Politics check :").append(mBoxPolitics.isChecked());
        resultBox.append("Sports check :").append(mBoxSports.isChecked());
        resultBox.append("Travel check :").append(mBoxTravel.isChecked());

        Log.d("TestCheckBox", resultBox.toString());

    }
    }

