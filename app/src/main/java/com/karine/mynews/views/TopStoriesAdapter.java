package com.karine.mynews.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karine.mynews.R;
import com.karine.mynews.models.TopStories;

import java.util.List;

/**
 * Created by <Karine> on <DATE-DU-JOUR>.
 */
public class TopStoriesAdapter extends RecyclerView.Adapter<TopstoriesViewHolder> {

    private List <TopStories> mTopStories;

    //Constructor
    public TopStoriesAdapter(List<TopStories> topStories) {
        this.mTopStories = topStories;
    }
    //Create viewholder
    @Override
    public TopstoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_item, parent, false);

        return new TopstoriesViewHolder(view);
    }

    //Update view holder
    @Override
    public void onBindViewHolder(TopstoriesViewHolder viewHolder, int position) {
        viewHolder.updateWithTopStories(this.mTopStories.get(position));
    }

    //Return the total count of items in the list
    @Override
    public int getItemCount() {
        return this.mTopStories.size();
    }
}
