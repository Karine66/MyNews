package com.karine.mynews.controllers.activities;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.karine.mynews.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SearchActivity extends AppCompatActivity implements OnClickListener {


    private static final String SHARED_PREFS_SEARCH = "";
    private static final String SEARCH = "search";
    private static final String DATE_PREF = "";
    private static String beginDate;
    private static String endDate;
    private static String inputSearch;
    @BindView(R.id.search_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.search)
    RelativeLayout mRelativeLayout;
    @BindView(R.id.input_searchLayout)
    TextInputLayout mInputSearch;
    @BindView(R.id.et_beginDate)
    EditText mBeginDate;
    @BindView(R.id.et_endDate)
    EditText mEndDate;
    @BindView(R.id.et_Search)
    EditText mEtSearch;
    @BindView(R.id.checkbox_container)
    RelativeLayout mCheckBox;
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
    @BindView(R.id.search_btn)
    Button mBtnSearch;
    //Date picker
    private DatePickerDialog mBeginDateDialog;
    private DatePickerDialog mEndDateDialog;
    //For date
    private SimpleDateFormat mDateFormat;
    private String resultBox;
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
        this.focusSearch();
        this.hideKeyboard();

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
            mEtSearch.setError("Field can't be empty");
//            Toast.makeText(getApplicationContext(), "Search Field can't be empty", Toast.LENGTH_SHORT).show();
            return false;

        } else {
            mEtSearch.setError(null);
            return true;
        }
    }

    //Save data : dates, search for SearchResultsFragment
    public void saveData() {

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Editor editor = sharedPref.edit();
        //For save dates
        editor.putString("begindate", dateConvertForSearch(mBeginDate.getText().toString()));
        editor.putString("enddate", dateConvertForSearch(mEndDate.getText().toString()));
        //For save search query
        editor.putString("search", mInputSearch.getEditText().getText().toString());

        editor.apply();

    }

    //Verify field dates format & if begin is not after enddate
    public boolean validDate() {

        String date1 = mBeginDate.getText().toString();
        String date2 = mEndDate.getText().toString();

        if (date1.isEmpty() && date2.isEmpty()) {
            return true;
        }

        //Verify dates format
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        Date fromDate = null;
        Date toDate = null;
        try {
            fromDate = dateFormat.parse(date1.trim());
            toDate = dateFormat.parse(date2.trim());
            Log.d("Date parsée", fromDate.toString());
            Log.d("Date parsée", toDate.toString());
            //Verify beginDate is not after endDate
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
        //Save data checkbox for SearchFragmentsResult
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Editor editor = sharedPref.edit();
        editor.putString("resultBox", resultBox.toString());
        editor.apply();
    }

    //    Verify checked box and field search
    public void addListenerButton() {

        mBtnSearch.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                confirmSearch();
                validDate();
                testCheckBox();
                saveData();

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
            return;
        }
        if (!validDate()) {
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

    //Convert date for search
    public String dateConvertForSearch(String dateToFormat) {

        try {
            if (dateToFormat != null && !dateToFormat.isEmpty()) {
                SimpleDateFormat sdf = null;
                Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateToFormat);
                sdf = new SimpleDateFormat("yyyyMMdd");
                return sdf.format(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
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

    //Hide keyboard when searchfield is not focused
    public void focusSearch() {
        mEtSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (v == mEtSearch) {
                    if (hasFocus) {
                        ((InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(mEtSearch, InputMethodManager.SHOW_FORCED);

                    } else {
                        ((InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(mEtSearch.getWindowToken(), 0);
                    }


                }
            }
        });
    }
}


