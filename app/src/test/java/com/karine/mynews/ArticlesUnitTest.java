package com.karine.mynews;

import com.karine.mynews.models.NYTArticle;

import org.junit.Before;
import org.junit.Test;

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
}