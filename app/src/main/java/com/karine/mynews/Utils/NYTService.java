package com.karine.mynews.Utils;


import com.karine.mynews.models.MostPopularAPI.MostPopular;
import com.karine.mynews.models.SearchAPI.Search;
import com.karine.mynews.models.TopStoriesAPI.TopStories;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by <Karine> on <DATE-DU-JOUR>.
 */
public interface NYTService {

    String API_KEY = "api-key=qAZiSFmOLvMctKNYLABeqsR16AWAEz0R";

    //Create EndPoint
    //TopStories API Request
    @GET("svc/topstories/v2/{section}.json?" + API_KEY)
    Observable<TopStories> getTopStories(@Path("section") String section);
    //MostPopular API (last 7 days)
    @GET("svc/mostpopular/v2/{section}/7.json?" + API_KEY)
    Observable<MostPopular> getMostPopular(@Path("section") String section);
    //ArticlesSearch API with dates
    @GET("svc/search/v2/articlesearch.json?sort=newest&" + API_KEY)
    Observable<Search> getSearch(@Query("q") String query, @Query("fq")String filterQuery,@Query("begin_date") String begin_Date, @Query("end_date") String end_Date);
    //ArticlesSearch API without dates
    @GET("svc/search/v2/articlesearch.json?sort=newest&" + API_KEY)
    Observable<Search> getSearchWithoutDates(@Query("q") String query, @Query("fq")String filterQuery);
}
