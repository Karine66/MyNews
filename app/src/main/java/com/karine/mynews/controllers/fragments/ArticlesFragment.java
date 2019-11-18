package com.karine.mynews.controllers.fragments;


import android.content.Intent;
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

import com.bumptech.glide.Glide;
import com.karine.mynews.R;
import com.karine.mynews.Utils.ItemClickSupport;
import com.karine.mynews.Utils.NYTStreams;
import com.karine.mynews.controllers.activities.WebViewActivity;
import com.karine.mynews.models.MostPopularAPI.MostPopular;
import com.karine.mynews.models.NYTArticle;
import com.karine.mynews.models.NYTResultsAPI;
import com.karine.mynews.models.TopStoriesAPI.TopStories;
import com.karine.mynews.views.ArticlesAdapter;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticlesFragment extends Fragment {

    public static final String URL = "url";
    /**
     * Declarations
     */
    @BindView(R.id.fragment_rvArticles)
    RecyclerView mRecyclerView;
    @BindView(R.id.fragment_tvArticles)
    TextView mTextView;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout mSwipeRefreshLayout;
    /**
     * Declare List NYTArticles et Adapter
     */
    private ArrayList<NYTArticle> mArticles;
    private ArticlesAdapter mAdapter;

    /**
     * For Data
     */
    private Disposable mDisposable;
    private int position;


    public ArticlesFragment() {
        // Required empty public constructor
    }

    /**
     * viewPager position and display informations
     *
     * @param position
     * @return
     */
    public static ArticlesFragment newInstance(int position) {
        ArticlesFragment fragment = new ArticlesFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        fragment.setArguments(bundle);
        return fragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_articles, container, false);
        ButterKnife.bind(this, view);
        this.position = getArguments().getInt("position");
        this.configureRecyclerView();
        this.configureSwipeRefreshLayout();
        this.executeHttpRequestWithRetrofit();
        this.configureOnClickRecyclerView();

        return view;
    }

    /**
     * Dispose subscription
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        this.disposeWhenDestroy();
    }

    /**
     * Configuration RecyclerView
     * Configure RecyclerView, Adapter, LayoutManager & glue it
     */
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

    /**
     * Configure item click on RecyclerView
     */
    private void configureOnClickRecyclerView() {
        ItemClickSupport.addTo(mRecyclerView, R.layout.fragment_item)
                .setOnItemClickListener((recyclerView, position, v) -> {

                    Log.d("Item", "Position : " + position);

                    NYTArticle mAdapterUrlArticles = mAdapter.getUrlArticles(position);
                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra(URL, mAdapterUrlArticles.getUrl());
                    startActivity(intent);

                });
    }

    /**
     * Configurer SwipeRefresh
     */
    private void configureSwipeRefreshLayout() {
        mSwipeRefreshLayout.setOnRefreshListener(this::executeHttpRequestWithRetrofit);
    }

    /**
     * HTTP RX JAVA
     * Execute stream
     */
    private void executeHttpRequestWithRetrofit() {

        switch (position) {

            case 0:
                topstoriesHome();
                break;
            case 1:
                mostPopular();
                break;
            case 2:
                business();
                break;
        }
    }

    /**
     * Execute stream by category
     */
    private void topstoriesHome() {
        this.mDisposable = NYTStreams.streamFetchTopStories("home")
                .subscribeWith(new DisposableObserver<TopStories>() {
                    @Override
                    public void onNext(TopStories section) {
                        NYTResultsAPI nytResultsAPI = NYTResultsAPI.createNYTResultsApiFromTopStories(section);
                        updateUI(nytResultsAPI);
                        Log.d("Tag", "test onNext");
                    }

                    @Override
                    public void onComplete() {
                        Log.e("ON_Complete", "Test onComplete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("onErrorHome", Log.getStackTraceString(e));
                    }

                });
    }

    private void mostPopular() {
        this.mDisposable = NYTStreams.streamFetchMostPopular("viewed")
                .subscribeWith(new DisposableObserver<MostPopular>() {
                    @Override
                    public void onNext(MostPopular section) {
                        NYTResultsAPI nytResultsAPI = NYTResultsAPI.createResultsApiFromMostPopular(section);
                        updateUI(nytResultsAPI);
                    }

                    @Override
                    public void onComplete() {

                        Log.e("ON_Complete", "Test onComplete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("onErrorMP", Log.getStackTraceString(e));
                    }
                });
    }

    private void business() {

        this.mDisposable = NYTStreams.streamFetchTopStories("business")
                .subscribeWith(new DisposableObserver<TopStories>() {
                    @Override
                    public void onNext(TopStories section) {
                        NYTResultsAPI nytResultsAPI = NYTResultsAPI.createNYTResultsApiFromTopStories(section);
                        updateUI(nytResultsAPI);
                    }

                    @Override
                    public void onComplete() {
                        Log.d("ON_Complete", "Test onComplete");
                    }


                    @Override
                    public void onError(Throwable e) {
                        Log.e("onErrorBusiness", Log.getStackTraceString(e));
                    }
                });
    }

    /**
     * updte UI with articles list
     *
     * @param nytResultsAPI
     */
    public void updateUI(NYTResultsAPI nytResultsAPI) {

        mSwipeRefreshLayout.setRefreshing(false);

        mArticles.clear();
        mArticles.addAll(nytResultsAPI.getNYTArticles());
        sortArticles(mArticles);

        mAdapter.notifyDataSetChanged();
    }

    /**
     * Dispose subscription
     */
    private void disposeWhenDestroy() {
        if (this.mDisposable != null && !this.mDisposable.isDisposed())
            this.mDisposable.dispose();
    }

    /**
     * Sort Articles in RecyclerView by descending order
     *
     * @param mArticles
     */
    public void sortArticles(ArrayList<NYTArticle> mArticles) {
        Collections.sort(mArticles, (o1, o2) -> o2.getPublishedDate().compareTo(o1.getPublishedDate()));
    }


}





