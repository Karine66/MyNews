package com.karine.mynews.Utils;

import com.karine.mynews.models.Business;
import com.karine.mynews.models.MostPopular;
import com.karine.mynews.models.TopStories;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by <Karine> on <DATE-DU-JOUR>.
 */
public interface NYTService {

    String API_KEY = "api-key=qAZiSFmOLvMctKNYLABeqsR16AWAEz0R";
    //Create EndPoint
    @GET("svc/topstories/v2/home.json?" + API_KEY)
    Call<List<TopStories>> getTopStories(@Path("home") String section);

    @GET("svc/mostpopular/v2/viewed/7.json?" + API_KEY)
    Call<List<MostPopular>> getMostPopular(@Path("viewed") String section);

    @GET("svc/search/v2/articlessearch.json?q=business&" + API_KEY)
    Call<List<Business>> getBusiness(@Path("business") String section);


    //return retrofit object
    Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://developer.nytimes.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
}
