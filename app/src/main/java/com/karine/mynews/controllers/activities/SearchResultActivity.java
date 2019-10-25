package com.karine.mynews.controllers.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.karine.mynews.R;
import com.karine.mynews.Utils.NYTStreams;
import com.karine.mynews.models.NYTArticle;
import com.karine.mynews.models.NYTResultsAPI;
import com.karine.mynews.models.SearchAPI.Search;
import com.karine.mynews.views.ArticlesAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;



public class SearchResultActivity extends AppCompatActivity {


    private static final String SHARED_PREFS_SEARCH = "sharedPrefsSearch";
    private static final String SEARCH ="search" ;

    private static final String DATE_PREF = "datepref";
    private static final String BEGIN_DATE = "begindate";
    private static final String END_DATE = "enddate";


    @BindView(R.id.search_result_toolbar)
    Toolbar mSearchResultToolbar;

    private String search;
    private String beginDate;
    private String endDate;
    private String date1;
    private String date2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        ButterKnife.bind(this);
        this.configureToolbar();



    }

    //create toolbar
    private void configureToolbar() {

        //Set the toolbar
        setSupportActionBar(mSearchResultToolbar);
        //Get a support Action Bar corresponding to this Toolbar
        ActionBar actionBar = getSupportActionBar();
        //Enable the Up button
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Search Results");


    }


}
