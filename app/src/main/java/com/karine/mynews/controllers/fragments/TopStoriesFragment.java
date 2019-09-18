package com.karine.mynews.controllers.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.karine.mynews.R;
import com.karine.mynews.Utils.NYTStreams;
import com.karine.mynews.models.TopStoriesAPI.TopStories;
import com.karine.mynews.views.ArticlesAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopStoriesFragment extends Fragment {

    //declarations
    @BindView(R.id.fragment_rvtopstories) RecyclerView mRecyclerView;
    @BindView(R.id.fragment_tvtopstories) TextView mTextView;

    //Declare top stories et Adapter
    private ArrayList mTopStories;
    private ArticlesAdapter mAdapter;

    //For Data
    private Disposable mDisposable;

    public TopStoriesFragment() {
    }

    public static TopStoriesFragment newInstance() {
        return (new TopStoriesFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top_stories, container, false);
        ButterKnife.bind(this, view);
        this.configureRecyclerView();
        this.executeHttpRequestWithRetrofit();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    //Configuration Recycler View
    //Configure RecyclerView, Adapter, LayoutManager & glue it
    private void configureRecyclerView() {
        //Reset List
        this.mTopStories = new ArrayList<>();
        //Create adapter
        this.mAdapter = new ArticlesAdapter(this.mTopStories);
        // Attach the adapter to the recyclerview
        this.mRecyclerView.setAdapter(this.mAdapter);
        //Set Layout manager
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    //HTTP RX Java
    // Execute Stream
    private void executeHttpRequestWithRetrofit() {
        //Update UI
        this.updateUIWhenStartingHTTPRequest();
        //Execute the stream subscribing to Observable
        this.mDisposable = NYTStreams.streamFetchTopStories("home").subscribeWith(new DisposableObserver <TopStories>() {
            @Override
            public void onNext(TopStories home) {

                updateUIWithTopStories(home);

            }

            @Override
            public void onError(Throwable e) {
            Log.e("onError", Log.getStackTraceString(e));
            }

            @Override
            public void onComplete() {
                Log.e("ON_Complete", "Test onComplete");
            }
        });
    }

    private void disposeWhenDestroy() {
        if (this.mDisposable != null && !this.mDisposable.isDisposed())
            this.mDisposable.dispose();
    }

     //Update UI


    private void updateUIWhenStartingHTTPRequest() {

       this.mTextView.setText("Downloading...");
    }

    private void updateUIWhenStopingHTTPRequest(String response) {
        this.mTextView.setText(response);
    }

    private void updateUIWithTopStories(TopStories topStories) {
//                 StringBuilder stringBuilder = new StringBuilder();
//            for (TopStories result : mTopStories) {
//                stringBuilder.append("-" + result.getSection() + "\n");


            updateUIWhenStopingHTTPRequest(topStories.getResults().get(0).getTitle());


      }
    }

