package com.karine.mynews.Utils;


import com.karine.mynews.models.MostPopularAPI.MostPopular;
import com.karine.mynews.models.TopStoriesAPI.TopStories;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;



/**
 * Created by <Karine> on <DATE-DU-JOUR>.
 */
public class NYTStreams {

    //Create stream TopStories
    public static Observable<TopStories> streamFetchTopStories(String section) {
        NYTService mNYTService = NYTRetrofitObject.retrofit.create(NYTService.class);
        return mNYTService.getTopStories(section)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    //Create stream MostPopular
    public static Observable<MostPopular> streamFetchMostPopular(String section) {
        NYTService mNYTService = NYTRetrofitObject.retrofit.create(NYTService.class);
        return mNYTService.getMostPopular(section)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }
//    //Create stream TopStories Business
//    public static Observable<TopStories> streamFetchBusiness(String business) {
//        NYTService mNYTService = NYTRetrofitObject.retrofit.create(NYTService.class);
//        return mNYTService.getBusiness(business)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .timeout(10, TimeUnit.SECONDS);
//    }
}