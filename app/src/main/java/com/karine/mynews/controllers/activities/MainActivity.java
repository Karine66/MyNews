package com.karine.mynews.controllers.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.karine.mynews.R;
import com.karine.mynews.adapters.PageAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Configure ViewPager
        this.configureViewPagerAndTabs();
        this.configureToolbar();
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        //inflate menu and add it to the Toolbar
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    private void configureViewPagerAndTabs() {
        //Get ViewPager from layout
        ViewPager pager = (ViewPager)findViewById(R.id.activity_main_viewpager);
        //Set Adapter PageAdapter
        pager.setAdapter(new PageAdapter(getSupportFragmentManager()));
        //Get TabLayout from layout
        TabLayout tabs = (TabLayout)findViewById(R.id.activity_main_tabs);
        //Join TabLayout and viewPager together
        tabs.setupWithViewPager(pager);
        //Tabs have the same width
        tabs.setTabMode(TabLayout.MODE_FIXED);
    }

    private void configureToolbar() {
        //Get Toolbar inside activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        //Set Toolbar
        setSupportActionBar(toolbar);
    }

}
