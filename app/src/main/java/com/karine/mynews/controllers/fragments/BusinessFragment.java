package com.karine.mynews.controllers.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karine.mynews.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusinessFragment extends Fragment {

    public static BusinessFragment newInstance() {
        return (new BusinessFragment());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_business, container, false);
    }
}


