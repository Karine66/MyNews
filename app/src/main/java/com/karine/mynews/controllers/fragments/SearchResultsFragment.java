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

import com.bumptech.glide.Glide;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchResultsFragment extends Fragment {
    //For Design déclare recycler view
    @BindView(R.id.fragment_rvSearchResults)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private ArrayList<NYTArticle> mArticles;
    private ArticlesAdapter mAdapter;
    private Disposable mDisposable;


    public SearchResultsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_results, container, false);
        ButterKnife.bind(this, view);
        this.configureRecyclerView();
        this.executeHttpRequestWithRetrofit();
        this.configureSwipeRefreshLayout();
        return view;
    }

    //configure SwipeRefresh Layout
    private void configureSwipeRefreshLayout() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                executeHttpRequestWithRetrofit();
            }
        });
    }

    //Dispose subscription
    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    //Configure RecyclerView, Adapter, LayoutManager & glue it
    private void configureRecyclerView() {
        //Reset List
        this.mArticles = new ArrayList<>();
        //Create adapter
        this.mAdapter = new ArticlesAdapter(this.mArticles, Glide.with(this));
        // Attach the adapter to the recyclerview
        this.mRecyclerView.setAdapter(this.mAdapter);
        //Set Layout manager
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }


    private void executeHttpRequestWithRetrofit() {


        this.mDisposable = NYTStreams.streamFetchSearch("search", "fq", "begin_date", "end_Date")
                .subscribeWith(new DisposableObserver<Search>() {

                    @Override
                    public void onNext(Search response) {
                        NYTResultsAPI nytResultsAPI = NYTResultsAPI.createResultsAPIFromSearch(response);
                        updateUI(nytResultsAPI);
                        Log.d("TestOnNextSearch", response.getResponse().getDocs().toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("ON_Complete", "Test onComplete");

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("onErrorHome", Log.getStackTraceString(e));
                    }
                });
    }

    private void disposeWhenDestroy() {
        if (this.mDisposable != null && !this.mDisposable.isDisposed())
            this.mDisposable.dispose();

    }

    public void updateUI(NYTResultsAPI nytResultsAPI) {

        mSwipeRefreshLayout.setRefreshing(false);

        mArticles.clear();
        mArticles.addAll(nytResultsAPI.getNYTArticles());
        mAdapter.notifyDataSetChanged();

    }

}
