package com.karine.mynews.controllers.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.karine.mynews.R;
import com.karine.mynews.Utils.NYTCalls;
import com.karine.mynews.Utils.NetworkAsyncTask;
import com.karine.mynews.models.TopStories;
import com.karine.mynews.views.TopStoriesAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopStoriesFragment extends Fragment implements NetworkAsyncTask.Listeners, NYTCalls.Callbacks {

    //declarations
    @BindView(R.id.fragment_rvtopstories) RecyclerView mRecyclerView;
    @BindView(R.id.fragment_tvtopstories) TextView mTextView;

    //Declare top stories et Adapter
    private List<TopStories> mTopStories;
    private TopStoriesAdapter mAdapter;

    public TopStoriesFragment(){}

    public static TopStoriesFragment newInstance() {
        return (new TopStoriesFragment());
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_top_stories, container, false);
        ButterKnife.bind(this,view);
        this.configureRecyclerView();
        this.executeHTTPRequest();
        this.executeHttpRequestWithRetrofit();
        return view;
    }
    //Configuration Recycler View
    //Configure RecyclerView, Adapter, LayoutManager & glue it
    private void configureRecyclerView() {
        //Reset List
        this.mTopStories = new ArrayList<>();
        //Create adapter
        this.mAdapter = new TopStoriesAdapter(this.mTopStories);
        // Attach the adapter to the recyclerview
        this.mRecyclerView.setAdapter(this.mAdapter);
        //Set Layout manager
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    //HTTP Retrofit
    // Execute HTTP request and update UI
    private void executeHttpRequestWithRetrofit(){
        this.updateUIWhenStartingHTTPRequest();
        NYTCalls.fetchTopStories(this, "section");
    }
    //Override callback methods
    @Override
    public void onResponseTopStories (List<TopStories> section) {
        if(section !=null) this.updateUIWithListTopStories(section);
    }
    @Override
    public void onFailureTopStories() {
        this.updateUIWhenStopingHTTPRequest("Error");
    }
    //HTTP request
    private void executeHTTPRequest() {
        new NetworkAsyncTask(this).execute("https://api.nytimes.com/svc/topstories/v2/home.json?api-key=qAZiSFmOLvMctKNYLABeqsR16AWAEz0R");
    }
    @Override
    public void onPreExecute() {
        this.updateUIWhenStartingHTTPRequest();
    }
    @Override
    public void doInBackground() {}

    @Override
    public void onPostExecute(String json) {
        this.updateUIWhenStopingHTTPRequest(json);
    }
    //Update UI
    private void updateUIWhenStartingHTTPRequest() {
        this.mTextView.setText("Downloading...");
    }
    private void updateUIWhenStopingHTTPRequest(String response) {
        this.mTextView.setText(response);
    }
    private void updateUIWithListTopStories(List<TopStories> section) {
        StringBuilder stringBuilder = new StringBuilder();
        for(TopStories result : section) {
            stringBuilder.append("-"+result.getSection()+"\n");
        }

            updateUIWhenStopingHTTPRequest(stringBuilder.toString());
        }
    }
