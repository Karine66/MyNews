package com.karine.mynews.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karine.mynews.R;

import java.util.ArrayList;


/**
 * Created by <Karine> on <DATE-DU-JOUR>.
 */
public  class ArticlesAdapter extends RecyclerView.Adapter<ArticlesViewHolder> {

    private ArrayList mTopStories;

    //Constructor
    public ArticlesAdapter(ArrayList topStories) {
        this.mTopStories = topStories;
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
        viewHolder.updateWithTopStories(this.mTopStories);

    }


    @Override
    public int getItemCount() {
        return this.mTopStories.size();
    }
}
