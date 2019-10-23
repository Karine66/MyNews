package com.karine.mynews.controllers.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
    private static final String PREF_BOX ="prefbox" ;
    private static final String BOX = "box";
    private static final String BOXARTS = "boxArts";


    @BindView(R.id.search_result_toolbar)
    Toolbar mSearchResultToolbar;

    private String search;
    private String beginDate;
    private String endDate;
    private String box;
    private boolean boxArts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        ButterKnife.bind(this);
        this.configureToolbar();
       this.loadDataSearch();
//        this.loadBox();


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


        public void loadDataSearch() {
            SharedPreferences sharedPrefSearch = getSharedPreferences(SHARED_PREFS_SEARCH, MODE_PRIVATE);
           search = sharedPrefSearch.getString(SEARCH,"");
            Log.d("TestSharedPrefsSearch",search );
        }

//        public void loadBox() {
//
//        SharedPreferences prefBox = getSharedPreferences(PREF_BOX, MODE_PRIVATE);
//        boxArts = prefBox.getBoolean(BOXARTS,false);
//        Log.d("TestBox", String.valueOf(boxArts));
//
//        }



}
