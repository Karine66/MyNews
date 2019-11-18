package com.karine.mynews.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;
import com.karine.mynews.R;
import com.karine.mynews.models.NYTArticle;

import java.util.ArrayList;
import java.util.List;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesViewHolder> {
    /**
     * Declarations
     */
    //For Data
    private ArrayList<NYTArticle> mArticles;
    //Declaring a Glide object
    private RequestManager glide;

    /**
     * Constructor
     *
     * @param mArticles
     * @param glide
     */
    public ArticlesAdapter(ArrayList<NYTArticle> mArticles, RequestManager glide) {

        this.mArticles = mArticles;
        this.glide = glide;
    }

    /**
     * Create viewHolder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ArticlesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_item, parent, false);

        return new ArticlesViewHolder(view);
    }

    /**
     * Update viewHolder
     *
     * @param viewHolder
     * @param position
     */
    @Override
    public void onBindViewHolder(ArticlesViewHolder viewHolder, int position) {
        viewHolder.updateWithArticles(this.mArticles.get(position), this.glide);
    }

    /**
     * For return articles
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return this.mArticles.size();
    }

    /**
     * For return article in webview
     *
     * @param position
     * @return
     */
    public NYTArticle getUrlArticles(int position) {
        List<NYTArticle> articles = mArticles;
        return articles.get(position);
    }
}
