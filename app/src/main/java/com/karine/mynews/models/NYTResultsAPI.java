package com.karine.mynews.models;

import com.karine.mynews.models.MostPopularAPI.MostPopular;
import com.karine.mynews.models.SearchAPI.Doc;
import com.karine.mynews.models.SearchAPI.Search;
import com.karine.mynews.models.TopStoriesAPI.Result;
import com.karine.mynews.models.TopStoriesAPI.TopStories;

import java.util.ArrayList;
import java.util.List;


public class NYTResultsAPI {
    /**
     * Declare articles list
     */
    private List<NYTArticle> mNYTArticles;


    /**
     * constructor
     *
     * @param nytArticles
     */
    public NYTResultsAPI(List<NYTArticle> nytArticles) {
        mNYTArticles = nytArticles;
    }

    /**
     * create NYTResultAPI (articles list) by category
     *
     * @param section
     * @return
     */
    public static NYTResultsAPI createNYTResultsApiFromTopStories(TopStories section) {
        List<NYTArticle> nytArticles = new ArrayList<>();

        for (Result result : section.getResults()) {
            NYTArticle nytArticle = new NYTArticle(result.getPublishedDate(), result.getSection(),
                    result.getTitle(), result.getUrl());
            nytArticle.setSubsection(result.getSubsection());
            if (result.getMultimedia().size() > 0)
                nytArticle.setMultimediaURL(result.getMultimedia().get(0).getUrl());
            nytArticles.add(nytArticle);
        }
        return new NYTResultsAPI(nytArticles);
    }

    public static NYTResultsAPI createResultsApiFromMostPopular(MostPopular section) {
        List<NYTArticle> nytArticles = new ArrayList<>();

        for (com.karine.mynews.models.MostPopularAPI.Result result : section.getResults()) {
            NYTArticle nytArticle = new NYTArticle(result.getPublishedDate(), result.getSection(),
                    result.getTitle(), result.getUrl());
            if (result.getMedia().size() > 0 && result.getMedia().get(0).getMediaMetadata().size() > 0)
                nytArticle.setMultimediaURL(result.getMedia().get(0).getMediaMetadata().get(0).getUrl());
            nytArticles.add(nytArticle);
        }
        return new NYTResultsAPI(nytArticles);
    }

    public static NYTResultsAPI createResultsAPIFromSearch(Search response) {
        List<NYTArticle> nytArticles = new ArrayList<>();

        for (Doc result : response.getResponse().getDocs()) {
            NYTArticle nytArticle = new NYTArticle(result.getPubDate(), result.getSectionName(),
                    result.getHeadline().getMain(), result.getWebUrl());
            if (result.getMultimedia().size() > 0)
                nytArticle.setMultimediaURL("https://static01.nyt.com/" + result.getMultimedia().get(0).getUrl());
            nytArticles.add(nytArticle);
        }
        return new NYTResultsAPI(nytArticles);
    }

    public static NYTResultsAPI createResultsAPIFromSearchWithoutDates(Search response) {
        List<NYTArticle> nytArticles = new ArrayList<>();

        for (Doc result : response.getResponse().getDocs()) {
            NYTArticle nytArticle = new NYTArticle(result.getPubDate(), result.getSectionName(),
                    result.getHeadline().getMain(), result.getWebUrl());
            if (result.getMultimedia().size() > 0)
                nytArticle.setMultimediaURL("https://static01.nyt.com/" + result.getMultimedia().get(0).getUrl());
            nytArticles.add(nytArticle);
        }
        return new NYTResultsAPI(nytArticles);
    }

    public List<NYTArticle> getNYTArticles() {
        return mNYTArticles;
    }

}
