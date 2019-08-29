package com.karine.mynews.Utils;

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
//Create EndPoint
    String API_KEY = "api-key=qAZiSFmOLvMctKNYLABeqsR16AWAEz0R";

    @GET("topstories/v2/{section}.json?" + API_KEY)
    Call<List<TopStories>> getTopStories(@Path("section") String section);

    @GET("mostpopular/v2/{section}.json?" + API_KEY)
    Call<List<MostPopular>> getMostPopular(@Path("section") String section);

    //return retrofit object
        public static final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://developer.nytimes.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
}
