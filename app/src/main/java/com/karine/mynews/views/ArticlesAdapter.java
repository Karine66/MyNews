package com.karine.mynews.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karine.mynews.R;
import com.karine.mynews.models.MostPopularAPI.MostPopular;
import com.karine.mynews.models.TopStoriesAPI.Result;
import com.karine.mynews.models.TopStoriesAPI.TopStories;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by <Karine> on <DATE-DU-JOUR>.
 */
public  class ArticlesAdapter extends RecyclerView.Adapter<ArticlesViewHolder> {

    //For Data
    private ArrayList mArticles;

    //Constructor
    public ArticlesAdapter(ArrayList mArticles) {
        this.mArticles = mArticles;
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
        viewHolder.updateWithArticles((TopStories) this.mArticles.get(position));

    }


    @Override
    public int getItemCount() {
        return this.mArticles.size();
    }

    public void updateData(List<Result> results) {
        this.mArticles.addAll(results);
        notifyDataSetChanged();

    }
}
