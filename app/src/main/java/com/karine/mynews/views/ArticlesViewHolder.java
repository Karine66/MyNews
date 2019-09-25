package com.karine.mynews.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.karine.mynews.R;
import com.karine.mynews.models.TopStoriesAPI.TopStories;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by <Karine> on <DATE-DU-JOUR>.
 */
public class ArticlesViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.fragment_tvArticles)
    TextView mTextView;
    private TopStories mTopStories;
    private ArrayAdapter<Object> mAdapter;

    public ArticlesViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithArticles(TopStories home) {
        this.mTextView.setText(home.getResults().size());
    }
}

//    public void updateWithBusiness(ArrayList business) {
//        this.mTextView.setText(business.size());
//    }
//    public void updateWithMostPopular (ArrayList viewed) {
//        this.mTextView.setText(viewed.size());
//    }
//
//    }


