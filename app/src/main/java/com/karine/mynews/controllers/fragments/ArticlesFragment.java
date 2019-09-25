package com.karine.mynews.controllers.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.karine.mynews.models.MostPopularAPI.MostPopular;
import com.karine.mynews.models.TopStoriesAPI.Result;
import com.karine.mynews.models.TopStoriesAPI.TopStories;
import com.karine.mynews.views.ArticlesAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticlesFragment extends Fragment {

    //declarations
    @BindView(R.id.fragment_rvArticles)
    RecyclerView mRecyclerView;

    @BindView(R.id.fragment_tvArticles)
     TextView mTextView;

    //Declare top stories et Adapter
    private ArrayList mArticles;
    private ArticlesAdapter mAdapter;

    //For Data
    private Disposable mDisposable;
    private int position;

    public ArticlesFragment() {
    }

    public static ArticlesFragment newInstance() {
        return (new ArticlesFragment());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_articles, container, false);
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
        this.mArticles = new ArrayList<>();
        //Create adapter
        this.mAdapter = new ArticlesAdapter(this.mArticles);
        // Attach the adapter to the recyclerview
        this.mRecyclerView.setAdapter(this.mAdapter);
        //Set Layout manager
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    //    HTTP RX Java
//     Execute Stream
    private void executeHttpRequestWithRetrofit() {
        //Update UI
        this.updateUIWhenStartingHTTPRequest();

        switch (position) {

            case 0:
                topstoriesHome();

                break;
//            case 1:
//                mostPopular();
//                break;
//            case 2:
//                business();
//                break;
        }
    }

    //Update UI
    private void updateUIWhenStartingHTTPRequest() {
        this.mTextView.setText("Downloading...");
    }

    private void topstoriesHome() {
        this.mDisposable = NYTStreams.streamFetchTopStories("home")
                .subscribeWith(new DisposableObserver<TopStories>() {
                    @Override
                    public void onNext(TopStories home) {
                        updateUIWithTopStories(home);
                       mAdapter.updateData(home.getResults());
                       Log.d("Tag", "test onNext");
                    }

                    @Override
                    public void onComplete () {
                        Log.e("ON_Complete", "Test onComplete");
                    }

                    @Override
                    public void onError (Throwable e){
                        Log.e("onError", Log.getStackTraceString(e));
                    }

                });
    }

//        private void mostPopular () {
//            this.mDisposable = NYTStreams.streamFetchMostPopular("viewed")
//                    .subscribeWith(new DisposableObserver<MostPopular>() {
//                        @Override
//                        public void onNext(MostPopular viewed) {
//                            updateUIWithMostPopular(viewed);
//                        }
//
//                        @Override
//                        public void onComplete() {
//
//                            Log.e("ON_Complete", "Test onComplete");
//                        }
//
//                        @Override
//                        public void onError(Throwable e) {
//                            Log.e("onError", Log.getStackTraceString(e));
//                        }
//                    });
//        }
//
//        private void business () {
//
//                this.mDisposable = NYTStreams.streamFetchBusiness("business")
//                        .subscribeWith(new DisposableObserver<TopStories>() {
//                            @Override
//                            public void onNext(TopStories business) {
//                               updateUIWithBusiness(business);
//                            }
//                            @Override
//                            public void onComplete () {
//                                Log.e("ON_Complete", "Test onComplete");
//                            }
//
//                            @Override
//                            public void onError (Throwable e){
//                                Log.e("onError", Log.getStackTraceString(e));
//                            }
//                        });
//            }

    private void updateUIWithTopStories(TopStories home) {
        updateUIWhenStopingHTTPRequest(home.getResults());
    }

//    private void updateUIWithMostPopular(MostPopular viewed) {
//        updateUIWhenStopingHTTPRequest(viewed.getResults());
//    }
//
//    private void updateUIWithBusiness(TopStories business) {
//        updateUIWhenStopingHTTPRequest(business.getResults());
//    }

    private void disposeWhenDestroy() {
        if (this.mDisposable != null && !this.mDisposable.isDisposed())
            this.mDisposable.dispose();
    }

    private List<Result> updateUIWhenStopingHTTPRequest(List<Result> results) {
        return results;
    }



    }

