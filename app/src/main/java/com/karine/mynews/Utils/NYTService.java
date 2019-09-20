package com.karine.mynews.Utils;

import android.app.AlertDialog;

import com.karine.mynews.models.BusinessAPI.Business;
import com.karine.mynews.models.MostPopularAPI.MostPopular;
import com.karine.mynews.models.TopStoriesAPI.TopStories;


import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by <Karine> on <DATE-DU-JOUR>.
 */
public interface NYTService {

    String API_KEY = "api-key=qAZiSFmOLvMctKNYLABeqsR16AWAEz0R";



    //Create EndPoint
    @GET("svc/topstories/v2/home.json?" + API_KEY)
    Observable<TopStories> getTopStories(@Query("section") String section);

    @GET("svc/mostpopular/v2/viewed/7.json?" + API_KEY)
    Observable<List<MostPopular>> getMostPopular(@Query("viewed") String section);

    @GET("svc/topstories/v2/business.json?" + API_KEY)
    Observable<List<Business>> getBusiness(@Query("section") String section);


}
