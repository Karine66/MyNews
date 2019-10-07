package com.karine.mynews.Utils;


import com.karine.mynews.models.MostPopularAPI.MostPopular;
import com.karine.mynews.models.TopStoriesAPI.TopStories;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by <Karine> on <DATE-DU-JOUR>.
 */
public interface NYTService {

    String API_KEY = "api-key=qAZiSFmOLvMctKNYLABeqsR16AWAEz0R";

    //Create EndPoint
    @GET("svc/topstories/v2/{section}.json?" + API_KEY)
    Observable<TopStories> getTopStories(@Path("section") String section);

    @GET("svc/mostpopular/v2/{section}/7.json?" + API_KEY)
    Observable<MostPopular> getMostPopular(@Path("section") String section);

//    @GET("svc/topstories/v2/business.json?" + API_KEY)
//    Observable<List<TopStories>> getBusiness(@Path("section") String section);


}
