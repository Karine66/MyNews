package com.karine.mynews.controllers.activities;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.karine.mynews.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity implements OnClickListener {

    //Date picker
    private DatePickerDialog mBeginDateDialog;
    private DatePickerDialog mEndDateDialog;
    //For date
    private SimpleDateFormat mDateFormat;

    @BindView(R.id.search_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.input_searchLayout)
    TextInputLayout mInputSearch;
    @BindView(R.id.et_beginDate)
    EditText mBeginDate;
    @BindView(R.id.et_endDate)
    EditText mEndDate;
    @BindView(R.id.editText_Search)
    EditText mEtSearch;
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
//    @BindViews({R.id.checkbox_arts, R.id.checkbox_politics, R.id.checkbox_business, R.id.checkbox_sports, R.id.checkbox_entrepreneurs, R.id.checkbox_travel})
//    List<CheckBox> mCheckBoxList;
    @BindView(R.id.search_btn)
    Button mBtnSearch;
    private String resultBox;
    private int m;
    private String date1;
    private String date2;
    private Date fromDate;
    private Date toDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        mDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.configureToolbar();
        this.focusDates();
        this.setDateField();
        this.addListenerButton();
        this.confirmSearch();

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

    // Field Search must be inquire
    private boolean validateSearch() {
        String searchInput = mInputSearch.getEditText().getText().toString().trim();

        if (searchInput.isEmpty()) {
            mInputSearch.setError("Field can't be empty");
            Toast.makeText(getApplicationContext(), "Search Field can't be empty", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            mInputSearch.setError(null);
            return true;
        }
    }

    //Verify field dates format & if begin is not after enddate
    public boolean validDate(String date1, String date2) {



        date1 = mBeginDate.getText().toString();
        date2 = mEndDate.getText().toString();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        Date fromDate = null;
        Date toDate = null;
        try {
            fromDate = dateFormat.parse(date1.trim());
            toDate = dateFormat.parse(date2.trim());
            Log.d("Date parsée", fromDate.toString());
            Log.d("Date parsée", toDate.toString());

            if (fromDate.after(toDate)) {
                Toast.makeText(getApplicationContext(), "BeginDate can't be after EndDate", Toast.LENGTH_SHORT).show();
                Log.d("TestDates", "La date debut ne peut être après la date de fin");
                return false;
            }
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Invalid date format", Toast.LENGTH_SHORT).show();
            Log.e("TestErreurDate", "Format de date invalide");
            return false;
        }
        return true;
    }
//        return true;
//    }

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
    //Trial with butterKnife checkbox
    //@OnCheckedChanged({R.id.checkbox_arts, R.id.checkbox_travel, R.id.checkbox_business, R.id.checkbox_sports, R.id.checkbox_entrepreneurs, R.id.checkbox_politics})
//void onChecked(boolean checked) {
//        Log.d("TestChecked", checked ? "Checked":"Unchecked");
////      Toast.makeText(this, checked? "Checked" :"Unchecked", Toast.LENGTH_SHORT).show();
//}

    //    Verify checked box and field search
    public void addListenerButton() {

        mBtnSearch.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                confirmSearch();
                validDate(date1, date2);
                testCheckBox();
            }
        });
    }

    //    Click on button search verify required/ toast if one checkbox is not checked
    public void confirmSearch() {

        if (!validateSearch()) {
            return;
        }
        if (!mBoxTravel.isChecked() && !mBoxPolitics.isChecked() && !mBoxSports.isChecked() &&
                !mBoxBusiness.isChecked() && !mBoxEntrepreneurs.isChecked() && !mBoxArts.isChecked()) {
            Toast.makeText(getApplicationContext(), "A least one category must be checked", Toast.LENGTH_SHORT).show();
        }if(!validDate(date1, date2)){
            return;
        } else {
            Intent searchResultIntent = new Intent(this, SearchResultActivity.class);
            startActivity(searchResultIntent);
        }
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
        mBeginDateDialog = new DatePickerDialog(this, new OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                mBeginDate.setText(mDateFormat.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        mEndDateDialog = new DatePickerDialog(this, new OnDateSetListener() {
            //For EndDate
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                mEndDate.setText(mDateFormat.format(newDate.getTime()));
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
    }

    //Click on Calendar
    @Override
    public void onClick(View view) {
        if (view == mBeginDate) {
            mBeginDateDialog.show();
        } else if (view == mEndDate) {
            mEndDateDialog.show();
        }
    }


}
