package com.karine.mynews.controllers.activities;

import android.app.Activity;
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
;
import com.karine.mynews.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;




public class SearchActivity extends AppCompatActivity implements OnClickListener {


    private static final String SHARED_PREFS_SEARCH = "";
    private static final String SEARCH = "search";
    private static final String DATE_PREF = "";


    //Date picker
    private DatePickerDialog mBeginDateDialog;
    private DatePickerDialog mEndDateDialog;
    //For date
    private SimpleDateFormat mDateFormat;

    @BindView(R.id.search_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.search)
    RelativeLayout mRelativeLayout;
    @BindView(R.id.input_searchLayout)
    TextInputLayout mInputSearch;
    @BindView(R.id.input_dateLayout)
    TextInputLayout mInputDate1;
    @BindView(R.id.et_beginDate)
    EditText mBeginDate;
    @BindView(R.id.et_endDate)
    EditText mEndDate;
    @BindView(R.id.et_Search)
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
    @BindViews({R.id.checkbox_arts, R.id.checkbox_politics, R.id.checkbox_business, R.id.checkbox_sports, R.id.checkbox_entrepreneurs, R.id.checkbox_travel})
    List<CheckBox> mCheckBoxList;
    @BindView(R.id.search_btn)
    Button mBtnSearch;
    private String resultBox;
    private String date1;
    private String date2;
    private Date fromDate;
    private Date toDate;
    private static String beginDate;
    private static String endDate;
    private static String inputSearch;
    private String strDate;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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
//        this.focusBeginDate();

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
        editor.putString("begindate", mBeginDate.getText().toString());
        editor.putString("enddate", mEndDate.getText().toString());
        //For save search query
        editor.putString("search", mInputSearch.getEditText().getText().toString());

        editor.apply();

    }

    public void convertDateForSearch(String strDate) {
        String fromDate = mBeginDate.getText().toString();
        String toDate = mEndDate.getText().toString();
        try {
            SimpleDateFormat dateInput = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);

            SimpleDateFormat dateOutput = new SimpleDateFormat("YYYY-MM-dd", Locale.US);
            Date date = dateInput.parse(strDate);
            fromDate = dateOutput.format(date);
            toDate = dateOutput.format(date);
            Log.d("Testformatdate1", fromDate);
            Log.d("TestformatDate2", toDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //Verify field dates format & if begin is not after enddate
    public boolean validDate(String date1, String date2) {

        date1 = mBeginDate.getText().toString();
        date2 = mEndDate.getText().toString();

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

//
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    public void checkboxTreatment() {
//
//        List <String> listBox = Arrays.asList("arts", "business", "entrepreneurs", "politics","sports","travel");
//        String listSeparated = String.join(" ", listBox);
//
//        if(mBoxArts.isChecked()) {
//            listBox.add("arts");
//        }
//        if (mBoxBusiness.isChecked()) {
//            listBox.add("business");
//        }
//        if (mBoxEntrepreneurs.isChecked()) {
//            listBox.add("entrepreneurs");
//        }
//        if(mBoxPolitics.isChecked()) {
//            listBox.add("politics");
//        }
//        if(mBoxSports.isChecked()) {
//            listBox.add("sports");
//        }
//        if(mBoxTravel.isChecked()) {
//            listBox.add("travel");
//
//        }
//        Log.d("TestlistBox", listBox.toString());
//
//    }

    //    Verify checked box and field search
    public void addListenerButton() {

        mBtnSearch.setOnClickListener(new OnClickListener() {


            @Override
            public void onClick(View v) {

                confirmSearch();
                validDate(date1, date2);
                testCheckBox();
                saveData();
//                checkboxTreatment();
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
        if (!validDate(date1, date2)) {
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

        mBeginDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
             if (mBeginDate.hasFocus()) {
             mBeginDate.setFocusable(true);
             mBeginDate.requestFocus();
             }
            }
          });
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



//    public void focusBeginDate () {
//        mBeginDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if(hasFocus) {
//                    mBeginDate.isFocused();
//                    mBeginDate.requestFocus();
//                }
//            }
//        });
//    }

}


