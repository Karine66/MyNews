package com.karine.mynews.Utils;

import com.karine.mynews.models.Business;
import com.karine.mynews.models.MostPopular;
import com.karine.mynews.models.TopStoriesAPI.TopStories;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by <Karine> on <DATE-DU-JOUR>.
 */
public interface NYTService {

    String API_KEY = "api-key=qAZiSFmOLvMctKNYLABeqsR16AWAEz0R";

    //Create EndPoint
    @GET("svc/topstories/v2/home.json?" + API_KEY)
    Call<TopStories> getTopStories(@Query("section") String section);

    @GET("svc/mostpopular/v2/viewed/7.json?" + API_KEY)
    Call<List<MostPopular>> getMostPopular(@Query("viewed") String section);

    @GET("svc/search/v2/articlessearch.json?q=business&" + API_KEY)
    Call<List<Business>> getBusiness(@Query("business") String section);


}
