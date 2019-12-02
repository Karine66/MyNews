package com.karine.mynews;

import com.karine.mynews.models.NYTArticle;
import com.karine.mynews.models.NYTResultsAPI;
import com.karine.mynews.models.SearchAPI.Doc;
import com.karine.mynews.models.SearchAPI.Headline;
import com.karine.mynews.models.SearchAPI.Multimedium;
import com.karine.mynews.models.SearchAPI.Search;
import com.karine.mynews.models.SearchAPI.Response;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.karine.mynews.models.NYTResultsAPI.createResultsAPIFromSearch;
import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SearchUnitTest {

    private String section;
    private String title;
    private String date;
    private String url;
    private String multimediaUrl;
    private NYTArticle nytArticle;
    private List<Multimedium> multimedia;
    private Multimedium mMultimedium;
    private Search search;



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
    public void  createNYTResultsApiFromSearchForTest() {

        nytArticle.setSection(section);
        nytArticle.setTitle(title);
        nytArticle.setPublishedDate(date);
        nytArticle.setUrl(url);
        nytArticle.setMultimediaURL(multimediaUrl);


        Doc articlesSearch = new Doc();
        Headline headline = new Headline();


        List<Multimedium> multimedia = new ArrayList<>();

        articlesSearch.setPubDate("2019-11-19");
        articlesSearch.setSectionName( "Business");
        articlesSearch.setHeadline(headline);
        headline.setMain("Trump is still here");
        articlesSearch.setWebUrl("https://api.nytimes.com/2019/11/19/business/Trump-is-still-here.html ");
        articlesSearch.setMultimedia(multimedia);

        mMultimedium = new Multimedium();
        mMultimedium.setUrl("images/2019/11/19/business/trump-thumbStandard.jpg");
        multimedia.add(mMultimedium);

        List<Doc> searchList = new ArrayList<>();
        searchList.add(articlesSearch);
        search = new Search();
        Response response = new Response();
        response.setDocs(searchList);
        search.setResponse(response);

        NYTResultsAPI articlesFromSearch = createResultsAPIFromSearch(search);

        assertEquals(nytArticle.getSection(), articlesFromSearch.getNYTArticles().get(0).getSection());
        assertEquals(nytArticle.getTitle(), articlesFromSearch.getNYTArticles().get(0).getTitle());
        assertEquals(nytArticle.getPublishedDate(), articlesFromSearch.getNYTArticles().get(0).getPublishedDate());
        assertEquals(nytArticle.getUrl(), articlesFromSearch.getNYTArticles().get(0).getUrl());
        assertEquals(nytArticle.getMultimediaURL(),articlesFromSearch.getNYTArticles().get(0).getMultimediaURL());

    }

}