package com.karine.mynews.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;
import com.karine.mynews.R;
import com.karine.mynews.models.TopStoriesAPI.Result;

import java.util.ArrayList;


/**
 * Created by <Karine> on <DATE-DU-JOUR>.
 */
public  class ArticlesAdapter extends RecyclerView.Adapter<ArticlesViewHolder> {

    //For Data
    private ArrayList mArticles;
    //Declaring a Glide object
    private RequestManager glide;



    //Constructor
    public ArticlesAdapter(ArrayList mArticles, RequestManager glide) {

        this.mArticles = mArticles;
        this.glide = glide;
    }
    //Create viewholder
    @Override
    public ArticlesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_item, parent, false);

        return new ArticlesViewHolder(view);
    }

    //Update view holder
    @Override
    public void onBindViewHolder(ArticlesViewHolder viewHolder, int position) {
        viewHolder.updateWithArticles((Result) this.mArticles.get(position), this.glide);

    }


    @Override
    public int getItemCount() {
        return this.mArticles.size();
    }


}
