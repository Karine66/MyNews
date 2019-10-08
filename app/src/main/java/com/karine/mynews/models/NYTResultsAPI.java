package com.karine.mynews.models;

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
           NYTArticle nytArticle = new NYTArticle(result.getPublishedDate(), result.getSection(), result.getSubsection(),
                   result.getTitle(), result.getUrl());
       if(result.getMultimedia().size() > 0)
       nytArticle.setMultimediaURL(result.getMultimedia().get(0).getUrl());
       nytArticles.add(nytArticle);
       }
       return new NYTResultsAPI(nytArticles);
   }


}
