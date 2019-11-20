package com.karine.mynews;

import android.support.annotation.NonNull;

import com.karine.mynews.models.NYTArticle;
import com.karine.mynews.models.NYTResultsAPI;
import com.karine.mynews.models.TopStoriesAPI.Result;
import com.karine.mynews.models.TopStoriesAPI.TopStories;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by <Karine> on <DATE-DU-JOUR>.
 */
public class ResultArticlesUnitTest {

    private String section;
    private String title;
    private String date;
    private String url;
    private String multimediaUrl;
    private NYTArticle nytArticle;
    private Object TopStories;


    @Before
    public void NytArticle() {


        section = "Business";
        title = "Trump is still here";
        date = "2019-11-19";
        url ="https://api.nytimes.com/2019/11/19/business/Trump-is-still-here.html ";
        multimediaUrl = "https://static01.nyt.com/images/2019/11/19/business/trump-thumbStandard.jpg";

        nytArticle = new NYTArticle(section, title,date,url);
    }


    @Test
    public void  createNYTResultsApiFromTopStoriesForTest() {

        nytArticle.setSection(section);
        nytArticle.setTitle(title);
        nytArticle.setPublishedDate(date);
        nytArticle.setUrl(url);

        Result articlesTopStories = new Result();

       // articlesTopStories.setMultimedia(multimediaUrl);
        articlesTopStories.setPublishedDate( "2019-11-19");
        articlesTopStories.setSection( "Business");
        articlesTopStories.setTitle("Trump is still here");
        articlesTopStories.setUrl("https://api.nytimes.com/2019/11/19/business/Trump-is-still-here.html ");

       NYTResultsAPI articlesFromTopStories = NYTResultsAPI.createNYTResultsApiFromTopStories((com.karine.mynews.models.TopStoriesAPI.TopStories) TopStories);



    }

}
