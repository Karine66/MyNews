package com.karine.mynews.Utils;


import com.karine.mynews.models.TopStoriesAPI.TopStories;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;



/**
 * Created by <Karine> on <DATE-DU-JOUR>.
 */
public class NYTStreams {

    //Create stream
    public static Observable<TopStories> streamFetchTopStories(String section) {
    NYTService mNYTService = NYTRetrofitObject.retrofit.create(NYTService.class);
        return mNYTService.getTopStories(section)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .timeout(10, TimeUnit.SECONDS);
    }

}
