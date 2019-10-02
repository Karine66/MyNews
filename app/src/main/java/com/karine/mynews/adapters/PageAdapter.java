package com.karine.mynews.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.karine.mynews.controllers.fragments.ArticlesFragment;

/**
 * Created by <Karine> on <DATE-DU-JOUR>.
 */
public class PageAdapter extends FragmentPagerAdapter {

    //Constructor
    public PageAdapter(FragmentManager mgr) {
        super(mgr);
    }
    @Override
    public int getCount() {
        return (3); //number of pages to show
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0 :
                return ArticlesFragment.newInstance(0);
            case 1 :
                return ArticlesFragment.newInstance(1);
            case 2 :
                return ArticlesFragment.newInstance(2);
            default:
                return null;
        }
    }
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0 :
                return "Top Stories";
            case 1 :
                return "Most Popular";
            case 2 :
                return "Business";
            default:
                return null;
        }
    }
}
