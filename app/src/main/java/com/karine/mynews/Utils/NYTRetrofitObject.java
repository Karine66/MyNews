package com.karine.mynews.Utils;



import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class NYTRetrofitObject {

    /**
     * For return retrofit object
     */

    public static Retrofit retrofit = new Retrofit.Builder()
            //define root URL
            .baseUrl("https://api.nytimes.com/")
            //serialize Gson
            .addConverterFactory(GsonConverterFactory.create())
            //For RxJava
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
}
