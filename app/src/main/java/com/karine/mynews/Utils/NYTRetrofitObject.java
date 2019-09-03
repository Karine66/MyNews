package com.karine.mynews.Utils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by <Karine> on <DATE-DU-JOUR>.
 */
public  class NYTRetrofitObject {

    //return retrofit object
    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://developer.nytimes.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    NYTService mNYTService = retrofit.create(NYTService.class);
}
