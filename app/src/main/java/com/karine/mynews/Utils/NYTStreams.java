package com.karine.mynews.Utils;


import com.karine.mynews.models.MostPopularAPI.MostPopular;
import com.karine.mynews.models.SearchAPI.Search;
import com.karine.mynews.models.TopStoriesAPI.TopStories;

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

    //Create stream Search with dates
    public static Observable<Search> streamFetchSearch(String query, String filterQuery, String begin_Date, String end_Date ) {
        NYTService mNYTService = NYTRetrofitObject.retrofit.create(NYTService.class);
        return  mNYTService.getSearch(query, filterQuery, begin_Date, end_Date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

    //Create stream Search without dates
    public static Observable<Search> streamFetchSearchWithoutDates(String query, String filterQuery) {
        NYTService mNYTService = NYTRetrofitObject.retrofit.create(NYTService.class);
        return  mNYTService.getSearchWithoutDates(query, filterQuery)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }
}