package com.karine.mynews.controllers.activities;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;


import com.karine.mynews.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity implements OnClickListener {

    //Date picker
    private DatePickerDialog mBeginDateDialog;
    private DatePickerDialog mEndDateDialog;
    //For date
    private SimpleDateFormat mDateFormat;

    @BindView(R.id.search_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.et_beginDate) EditText mBeginDate;
    @BindView(R.id.et_endDate) EditText mEndDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        mDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.configureToolbar();
        this.focusDates();
        this.setDateField();
    }
    //create toolbar
   private void configureToolbar() {

        //Set the toolbar
        setSupportActionBar(mToolbar);
       //Get a support Action Bar corresponding to this Toolbar
       ActionBar actionBar = getSupportActionBar();
       //Enable the Up button
       assert actionBar != null;
       actionBar.setDisplayHomeAsUpEnabled(true);
       actionBar.setTitle("Search Articles");

   }
    //for input
   private void focusDates() {
        mBeginDate.setInputType(InputType.TYPE_NULL);
        mEndDate.setInputType(InputType.TYPE_NULL);
   }
   //For clickListeners Edittext and datePicker
   private void setDateField() {
        mBeginDate.setOnClickListener(this);
        mEndDate.setOnClickListener(this);
        //For BeginDate
       Calendar newCalendar = Calendar.getInstance();
       mBeginDateDialog = new DatePickerDialog(this, new OnDateSetListener(){

           public void onDateSet (DatePicker view, int year, int monthOfYear, int dayOfMonth) {
               Calendar newDate = Calendar.getInstance();
               newDate.set(year, monthOfYear, dayOfMonth);
               mBeginDate.setText(mDateFormat.format(newDate.getTime()));
           }

       }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
       mEndDateDialog = new DatePickerDialog(this, new OnDateSetListener(){
        //For EndDate
           public void onDateSet (DatePicker view, int year, int monthOfYear, int dayOfMonth) {
               Calendar newDate = Calendar.getInstance();
               newDate.set(year, monthOfYear, dayOfMonth);
               mEndDate.setText(mDateFormat.format(newDate.getTime()));
           }
       }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
   }
        //Click on Calendar
        @Override
        public void onClick (View view) {
        if(view == mBeginDate) {
            mBeginDateDialog.show();
        }else if(view == mEndDate) {
            mEndDateDialog.show();
        }
        }
}
