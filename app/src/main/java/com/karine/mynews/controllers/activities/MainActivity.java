package com.karine.mynews.controllers.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.security.ProviderInstaller;
import com.karine.mynews.R;
import com.karine.mynews.views.PageAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.activity_main_viewpager)
    ViewPager pager;
    @BindView(R.id.activity_main_tabs)
    TabLayout tabs;
    @BindView(R.id.activity_main_toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_main_drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.activity_main_nav_view)
    NavigationView mNavigationView;
    @SuppressLint("ResourceType")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //For compatibility api16 to api22
        try{
            ProviderInstaller.installIfNeeded(getApplicationContext());
        }catch (GooglePlayServicesRepairableException e) {
            //Indicates that Google Play services is out of date, disabled, etc
            Log.e("api", Log.getStackTraceString(e));
            //Prompt the user to install/update/enable Google play services
            GoogleApiAvailability.getInstance()
                    .showErrorNotification(getApplicationContext(), e.getConnectionStatusCode());

        }catch (GooglePlayServicesNotAvailableException e) {
            //Indicates a non recoverable error; the ProviderInstaller is not able to install an up to date Provide
            Log.e("api", Log.getStackTraceString(e));

        }

        //Configure all views
        this.configureViewPagerAndTabs();
        this.configureToolbar();
        this.configureDrawerLayout();
        this.configureNavigationView();
    }
    @Override
    public void onBackPressed() {
        //Handle back click to close menu
        if (this.mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate menu and add it to the Toolbar
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }
    /**
     * For NavigationDrawer
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //Handle Navigation Item click
        int id = item.getItemId();
        switch (id) {
            case R.id.activity_main_drawer_TP:
                pager.setCurrentItem(0);
                break;
            case R.id.activity_main_drawer_MP:
                pager.setCurrentItem(1);
                break;
            case R.id.actitivy_main_drawer_Business:
                pager.setCurrentItem(3);
                break;
            case R.id.activity_main_drawer_search:
                Intent searchIntent = new Intent(this, SearchActivity.class);
                startActivity(searchIntent);
                break;
            case R.id.actitivy_main_drawer_notification:
                Intent notifIntent = new Intent(this, NotificationsActivity.class);
                startActivity(notifIntent);
                break;
        }
        this.mDrawerLayout.closeDrawer(GravityCompat.START);
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
    /**
     * Configure drawerLayout for NavigationDrawer
     */
    private void configureDrawerLayout() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }
    /**
     * Configure NavigationView for NavigationDrawer
     */
    private void configureNavigationView() {
        mNavigationView.setNavigationItemSelectedListener(this);
    }
    /**
     * Switching on different menu items in toolbar
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Handle actions on menu items
        switch (item.getItemId()) {
            case R.id.menu_search:
                Intent searchIntent = new Intent(this, SearchActivity.class);
                startActivity(searchIntent);
                return true;
            case R.id.notif:
                Intent notifIntent = new Intent(this, NotificationsActivity.class);
                startActivity(notifIntent);
                return true;
            case R.id.help:
                Toast.makeText(this, "If you need help contact us : mynews@nyt.com", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.about:
                Toast.makeText(this, "NYT newspaper", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}




