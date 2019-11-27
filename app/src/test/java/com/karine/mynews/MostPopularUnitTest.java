package com.karine.mynews;

import com.karine.mynews.models.MostPopularAPI.MediaMetadatum;
import com.karine.mynews.models.MostPopularAPI.Medium;
import com.karine.mynews.models.MostPopularAPI.MostPopular;
import com.karine.mynews.models.MostPopularAPI.Result;
import com.karine.mynews.models.NYTArticle;
import com.karine.mynews.models.NYTResultsAPI;



import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


import static com.karine.mynews.models.NYTResultsAPI.createResultsApiFromMostPopular;
import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MostPopularUnitTest {

    private String section;
    private String title;
    private String date;
    private String url;
    private String multimediaUrl;
    private NYTArticle nytArticle;
    private MostPopular mMostPopular;
    private List<Medium> mMediumList;
    private MediaMetadatum mMediaMetadatum;
    private Medium mMedium;


    //Constructor
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
    public void  createNYTResultsApiFromMostPopularForTest() {

        nytArticle.setSection(section);
        nytArticle.setTitle(title);
        nytArticle.setPublishedDate(date);
        nytArticle.setUrl(url);
        nytArticle.setMultimediaURL(multimediaUrl);


        Result articlesMostPopular = new Result();
        List<MediaMetadatum> mediaMetadata = new ArrayList<>();
        List <Medium> mediumList = new ArrayList<>();
        articlesMostPopular.setPublishedDate( "2019-11-19");
        articlesMostPopular.setSection( "Business");
        articlesMostPopular.setTitle("Trump is still here");
        articlesMostPopular.setUrl("https://api.nytimes.com/2019/11/19/business/Trump-is-still-here.html ");
        articlesMostPopular.setMedia(mediumList);

        mMedium = new Medium();
        mMedium.setMediaMetadata(mediaMetadata);
        mediumList.add(mMedium);

        mMediaMetadatum = new MediaMetadatum();
        mMediaMetadatum.setUrl("https://static01.nyt.com/images/2019/11/19/business/trump-thumbStandard.jpg");
        mediaMetadata.add(mMediaMetadatum);

        List<Result> resultListMP = new ArrayList<>();
        resultListMP.add(articlesMostPopular);
        mMostPopular = new MostPopular();
        mMostPopular.setResults(resultListMP);

        NYTResultsAPI articlesFromMostPopular = createResultsApiFromMostPopular(mMostPopular);

        assertEquals(nytArticle.getSection(), articlesFromMostPopular.getNYTArticles().get(0).getSection());
        assertEquals(nytArticle.getTitle(), articlesFromMostPopular.getNYTArticles().get(0).getTitle());
        assertEquals(nytArticle.getPublishedDate(), articlesFromMostPopular.getNYTArticles().get(0).getPublishedDate());
        assertEquals(nytArticle.getUrl(), articlesFromMostPopular.getNYTArticles().get(0).getUrl());
        assertEquals(nytArticle.getMultimediaURL(),articlesFromMostPopular.getNYTArticles().get(0).getMultimediaURL());

    }

}