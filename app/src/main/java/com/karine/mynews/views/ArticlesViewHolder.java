package com.karine.mynews.views;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.karine.mynews.R;
import com.karine.mynews.models.NYTArticle;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.karine.mynews.Utils.DatesAndHoursConverter.dateFormat;

public class ArticlesViewHolder extends RecyclerView.ViewHolder {

    /**
     * Declarations
     */
    @BindView(R.id.fragment_item_date)
    TextView mdate;
    @BindView(R.id.fragment_item_section)
    TextView mSection;
    @BindView(R.id.fragment_item_title)
    TextView mTitle;
    @BindView(R.id.fragment_item_photo)
    ImageView mImageView;

    public ArticlesViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }

    /**
     * For update with articles and photos
     *
     * @param result
     * @param glide
     */

    @SuppressLint("SetTextI18n")
    public void updateWithArticles(NYTArticle result, RequestManager glide) {

        this.mdate.setText(dateFormat(result.getPublishedDate()));
        this.mTitle.setText(result.getTitle());
//            get subsection if exist
        if ((result.getSubsection() != null) && (!result.getSubsection().isEmpty())) {
            this.mSection.setText(result.getSection() + " > " + result.getSubsection());
        } else {
            this.mSection.setText(result.getSection());
        }
        // If no photo exist in NYT article
        if (result.getMultimediaURL() != null && !result.getMultimediaURL().isEmpty()) {
            glide.load(result.getMultimediaURL()).apply(RequestOptions.centerCropTransform()).into(mImageView);
        } else {
            mImageView.setImageResource(R.drawable.nyt);
        }
    }
}



