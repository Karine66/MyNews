package com.karine.mynews.models;

import com.karine.mynews.models.MostPopularAPI.MostPopular;
import com.karine.mynews.models.SearchAPI.Response;
import com.karine.mynews.models.SearchAPI.Search;
import com.karine.mynews.models.TopStoriesAPI.Result;

import com.karine.mynews.models.TopStoriesAPI.TopStories;

import java.util.ArrayList;
import java.util.List;


public class NYTResultsAPI {

    private List<NYTArticle> mNYTArticles;



   public NYTResultsAPI(List<NYTArticle> nytArticles) {
       mNYTArticles = nytArticles;


   }

   public List<NYTArticle> getNYTArticles() {
       return mNYTArticles;
   }





   public static NYTResultsAPI createNYTResultsApiFromTopStories(TopStories section) {
       List<NYTArticle> nytArticles = new ArrayList<>();

       for (Result result : section.getResults()) {
           NYTArticle nytArticle = new NYTArticle(result.getPublishedDate(), result.getSection(),
                   result.getTitle(), result.getUrl());
           nytArticle.setSubsection(result.getSubsection());
       if(result.getMultimedia().size() > 0)
       nytArticle.setMultimediaURL(result.getMultimedia().get(0).getUrl());
       nytArticles.add(nytArticle);
       }
       return new NYTResultsAPI(nytArticles);
   }

    public static NYTResultsAPI createResultsApiFromMostPopular (MostPopular section) {
        List<NYTArticle> nytArticles = new ArrayList<>();

        for (com.karine.mynews.models.MostPopularAPI.Result result : section.getResults()) {
            NYTArticle nytArticle = new NYTArticle(result.getPublishedDate(), result.getSection(),
                    result.getTitle(), result.getUrl());
            if(result.getMedia().size() > 0 && result.getMedia().get(0).getMediaMetadata().size()>0)
                nytArticle.setMultimediaURL(result.getMedia().get(0).getMediaMetadata().get(0).getUrl());
            nytArticles.add(nytArticle);
        }
        return new NYTResultsAPI(nytArticles);

    }
}
