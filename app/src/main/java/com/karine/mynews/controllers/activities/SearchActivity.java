package com.karine.mynews.controllers.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;


import com.karine.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.search_toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        this.configureToolbar();
    }

   private void configureToolbar() {

        //Set the toolbar
        setSupportActionBar(mToolbar);
       //Get a support Action Bar corresponding to this Toolbar
       ActionBar actionBar = getSupportActionBar();
       //Enable the Up button
       assert actionBar != null;
       actionBar.setDisplayHomeAsUpEnabled(true);
       getSupportActionBar().setTitle("Search Articles");

   }

}
