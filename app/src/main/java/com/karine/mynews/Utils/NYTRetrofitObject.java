package com.karine.mynews.Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by <Karine> on <DATE-DU-JOUR>.
 */
public  class NYTRetrofitObject {



    static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    //return retrofit object
    public static  Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://developer.nytimes.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();



//    NYTService mNYTService = retrofit.create(NYTService.class);


}
