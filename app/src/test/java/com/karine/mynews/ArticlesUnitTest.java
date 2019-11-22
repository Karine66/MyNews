package com.karine.mynews;

import com.karine.mynews.models.NYTArticle;
import com.karine.mynews.models.NYTResultsAPI;
import com.karine.mynews.models.TopStoriesAPI.Result;
import com.karine.mynews.models.TopStoriesAPI.TopStories;

import org.junit.Before;
import org.junit.Test;

import static com.karine.mynews.models.NYTResultsAPI.createNYTResultsApiFromTopStories;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ArticlesUnitTest {

    private String section;
    private String title;
    private String date;
    private String url;
    private String multimediaUrl;
    private NYTArticle nytArticle;
    private TopStories topStories;


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
    public void testSection() {
       nytArticle.setSection(section);
        assertEquals(section,nytArticle.getSection());
    }
    @Test
    public void testTitle() {
        nytArticle.setTitle(title);
        assertEquals(title, nytArticle.getTitle());
    }
    @Test
    public void testDate() {
        nytArticle.setPublishedDate(date);
        assertEquals(date, nytArticle.getPublishedDate());
    }
    @Test
    public void testUrl() {
        nytArticle.setUrl(url);
        assertEquals(url, nytArticle.getUrl());
    }
    @Test
    public void setMultimediaUrl() {
        nytArticle.setMultimediaURL(multimediaUrl);
        assertEquals(multimediaUrl, nytArticle.getMultimediaURL());
    }

//    @Test
//    public void  createNYTResultsApiFromTopStoriesForTest() {
//
//        nytArticle.setSection(section);
//        nytArticle.setTitle(title);
//        nytArticle.setPublishedDate(date);
//        nytArticle.setUrl(url);
//
//        Result articlesTopStories= new Result();
//
//
//        // articlesTopStories.setMultimedia(multimediaUrl);
//        articlesTopStories.setPublishedDate( "2019-11-19");
//        articlesTopStories.setSection( "Business");
//        articlesTopStories.setTitle("Trump is still here");
//        articlesTopStories.setUrl("https://api.nytimes.com/2019/11/19/business/Trump-is-still-here.html ");

//       NYTResultsAPI articlesFromTopStories = createNYTResultsApiFromTopStories(topStories);

//       topStories.getResults().add(articlesTopStories);
//
//
//        assertEquals(nytArticle.getSection(), articlesFromTopStories.getNYTArticles().get(0));
//        assertEquals(nytArticle.getTitle(), articlesFromTopStories.getNYTArticles().get(0));
//        assertEquals(nytArticle.getPublishedDate(), articlesFromTopStories.getNYTArticles().get(0));
//        assertEquals(nytArticle.getUrl(), articlesFromTopStories.getNYTArticles().get(0));

//    }

}