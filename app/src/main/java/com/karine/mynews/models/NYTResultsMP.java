package com.karine.mynews.models;

import com.karine.mynews.models.MostPopularAPI.MostPopular;
import com.karine.mynews.models.MostPopularAPI.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by <Karine> on <DATE-DU-JOUR>.
 */
public class NYTResultsMP {

    private List<NYTArticleMP> mNYTArticleMP;

    public NYTResultsMP( List<NYTArticleMP> nytArticlesMP) {
        mNYTArticleMP = nytArticlesMP;
    }

    public List<NYTArticleMP> getNYTArticlesMP() {
        return mNYTArticleMP;
    }

    public static NYTResultsMP createResultsApiFromMostPopular (MostPopular section) {
        List<NYTArticleMP> nytArticlesMP = new ArrayList<>();

        for (Result result : section.getResults()) {
            NYTArticleMP nytArticleMP = new NYTArticleMP(result.getPublishedDate(), result.getSection(),
                    result.getTitle(), result.getUrl());
            if(result.getMedia().size() > 0)
                nytArticleMP.setMediaURL(result.getMedia().get(0).getUrl());
            nytArticlesMP.add(nytArticleMP);
        }
        return new NYTResultsMP(nytArticlesMP);

    }
}
