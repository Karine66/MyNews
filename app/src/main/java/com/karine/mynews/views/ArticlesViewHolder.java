package com.karine.mynews.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.request.RequestOptions;
import com.karine.mynews.R;
import com.karine.mynews.controllers.fragments.ArticlesFragment;
import com.karine.mynews.models.TopStoriesAPI.Result;
import com.karine.mynews.models.TopStoriesAPI.TopStories;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by <Karine> on <DATE-DU-JOUR>.
 */
public class ArticlesViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.fragment_item_date) TextView mdate;
    @BindView(R.id.fragment_item_section) TextView mSection;
    @BindView(R.id.fragment_item_title) TextView mTitle;
    //ImageView
    @BindView(R.id.fragment_item_photo) ImageView mImageView;

    private TopStories mTopStories;
    private ArrayAdapter<Object> mAdapter;




    public ArticlesViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @SuppressLint("SetTextI18n")
    public void updateWithArticles(Result result, RequestManager glide) {


            this.mdate.setText(dateFormat(result.getPublishedDate()));
            this.mTitle.setText(result.getTitle());
            //get subsection if exist
            if((result.getSubsection() !=null)&&(!result.getSubsection().isEmpty())) {
                this.mSection.setText(result.getSection() + " > " + result.getSubsection());
            }else{
                this.mSection.setText(result.getSection());
            }
            if(result.getMultimedia() != null && !result.getMultimedia().isEmpty()) {
                glide.load(result.getMultimedia().get(0).getUrl()).apply(RequestOptions.centerCropTransform()).into(mImageView);
            }else{
                mImageView.setImageResource(R.drawable.nyt);
            }


}



    //Convert dates in dd/MM/yyy
    private String dateFormat (String dateString) {
    List<String> strings = Arrays.asList("yyyy-MM-dd", "yyyy-MM-dd'T'HH:mm:ssZ");

    for (String string : strings)
    {
        try
        {
            SimpleDateFormat sdf = null;
            Date date = new SimpleDateFormat(string).parse(dateString);
            sdf = new SimpleDateFormat("dd/MM/yyyy");
            return sdf.format(date);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
    }
        return "";
    }}



