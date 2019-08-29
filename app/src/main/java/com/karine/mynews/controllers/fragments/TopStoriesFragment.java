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
public class TopStoriesFragment extends Fragment  {


    public static TopStoriesFragment newInstance() {
        return (new TopStoriesFragment());

          }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_top_stories, container, false);


       }

}
