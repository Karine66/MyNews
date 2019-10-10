package com.karine.mynews.controllers.activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;



import com.karine.mynews.R;
import com.karine.mynews.adapters.PageAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.activity_main_viewpager) ViewPager pager;
    @BindView(R.id.activity_main_tabs) TabLayout tabs;
    @BindView(R.id.activity_main_toolbar) Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
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
        //Set Adapter PageAdapter
        pager.setAdapter(new PageAdapter(getSupportFragmentManager()));
        //Join TabLayout and viewPager together
        tabs.setupWithViewPager(pager);
        //Tabs have the same width
        tabs.setTabMode(TabLayout.MODE_FIXED);
    }

    private void configureToolbar() {
        //Set Toolbar
        setSupportActionBar(toolbar);
    }

    //Switching on different menu items
    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        //Handle actions on menu items
        switch (item.getItemId()) {
            case R.id.menu_search:
                    Intent searchIntent = new Intent(this, SearchActivity.class);
                    startActivity(searchIntent);
                    return true;

                    case R.id.menu_params:
                   //a faire ensuite
                    default :
                        return super.onOptionsItemSelected(item);
                }
            }
        }




