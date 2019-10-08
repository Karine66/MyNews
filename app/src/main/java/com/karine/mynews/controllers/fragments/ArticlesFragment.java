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


import com.bumptech.glide.Glide;
import com.karine.mynews.R;


import com.karine.mynews.Utils.ItemClickSupport;
import com.karine.mynews.Utils.NYTStreams;

import com.karine.mynews.models.MostPopularAPI.MostPopular;
import com.karine.mynews.models.NYTArticle;
import com.karine.mynews.models.NYTArticleMP;
import com.karine.mynews.models.NYTResultsAPI;
import com.karine.mynews.models.NYTResultsMP;

import com.karine.mynews.models.TopStoriesAPI.Result;
import com.karine.mynews.models.TopStoriesAPI.TopStories;
import com.karine.mynews.views.ArticlesAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


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

    @BindView(R.id.swipe_container)
    SwipeRefreshLayout mSwipeRefreshLayout;

    //Declare top stories et Adapter
    private ArrayList <NYTArticle>mArticles;
    private ArrayList<NYTArticleMP> mNYTArticleMP;
    private ArticlesAdapter mAdapter;

    //For Data
    private Disposable mDisposable;
    private int position;


    public ArticlesFragment() {
    }


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
        this.mAdapter = new ArticlesAdapter(this.mArticles, Glide.with(this));
        // Attach the adapter to the recyclerview
        this.mRecyclerView.setAdapter(this.mAdapter);
        //Set Layout manager
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    //Configure item click on RecyclerView
    private void configureOnClickRecyclerView() {
       ItemClickSupport.addTo(mRecyclerView, R.layout.fragment_item)
            .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                @Override
                public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                }
            });
        }


    private void configureSwipeRefreshLayout() {
        mSwipeRefreshLayout.setOnRefreshListener(() -> executeHttpRequestWithRetrofit());
    }

    //    HTTP RX Java
//     Execute Stream
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
                        Log.e("onError", Log.getStackTraceString(e));
                    }

                });
    }

        private void mostPopular () {
            this.mDisposable = NYTStreams.streamFetchMostPopular("viewed")
                    .subscribeWith(new DisposableObserver<MostPopular>() {
                        @Override
                        public void onNext(MostPopular section) {
                            NYTResultsMP nytResultsMP = NYTResultsMP.createResultsApiFromMostPopular(section);
                            updateUI(nytResultsMP);
                        }

                        @Override
                        public void onComplete() {

                            Log.e("ON_Complete", "Test onComplete");
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("onError", Log.getStackTraceString(e));
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
                        Log.e("ON_Complete", "Test onComplete");
                    }


                    @Override
                    public void onError(Throwable e) {
                        Log.e("onError", Log.getStackTraceString(e));
                    }
                });
    }

    public void updateUI(NYTResultsAPI nytResultsAPI {

        mSwipeRefreshLayout.setRefreshing(false);
        switch (position) {
            case 0 :
                mArticles.clear();
                mArticles.addAll(nytResultsAPI.getNYTArticles());
                break;
            case 1 :
                mArticles.clear();
                mArticles.addAll(nytResultsMP.getNYTArticlesMP());
                break;
            case 2 :
                mArticles.clear();
                mArticles.addAll(nytResultsAPI.getNYTArticles());
        }
       // sortArticles(mArticles);

        mAdapter.notifyDataSetChanged();
    }

    private void disposeWhenDestroy() {
        if (this.mDisposable != null && !this.mDisposable.isDisposed())
            this.mDisposable.dispose();
    }

    //Sort Articles in RecyclerView by descending order
    public void sortArticles(ArrayList mArticles) {
        Collections.sort(mArticles, (Comparator<Result>) (o1, o2) -> o2.getPublishedDate().compareTo(o1.getPublishedDate()));
    }

//    public void resultArticles() {
//        NYTResultsAPI resultArticles = new NYTResultsAPI();
//
//}

}





