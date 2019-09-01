package com.karine.mynews.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.karine.mynews.R;
import com.karine.mynews.models.TopStories;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by <Karine> on <DATE-DU-JOUR>.
 */
public class TopstoriesViewHolder extends RecyclerView.ViewHolder {

  @BindView(R.id.fragment_rvtopstories) TextView mTextView;

    public TopstoriesViewHolder (View itemView) {
        super(itemView);
        ButterKnife.bind(this.itemView);
    }

    public void updateWithTopStories(TopStories topStories){
        this.mTextView.setText(topStories.getSection());
    }

}
