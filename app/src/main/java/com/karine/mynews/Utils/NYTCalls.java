package com.karine.mynews.Utils;

import android.support.annotation.Nullable;

import com.karine.mynews.models.MostPopular;
import com.karine.mynews.models.TopStories;

import java.lang.ref.WeakReference;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by <Karine> on <DATE-DU-JOUR>.
 */
public class NYTCalls {
    //Creating Call Back
    public interface Callbacks {
        void onResponseTopStories(@Nullable List<TopStories> topStories);
        void onResponseMostPopular(@Nullable List<MostPopular> mostPopulars);
        void onFailureTopStories();
        void onFailureMostPopular();
    }
    //Start to fetch TopStories
    public static void fetchTopStories (Callbacks callbacks, String section) {
        //create a weak reference to callback
        final WeakReference<Callbacks> callbacksWeakReference = new WeakReference<Callbacks> (callbacks);
        //Get retrofit instance and the related endpoints
        NYTService nytService = NYTService.retrofit.create(NYTService.class);
        //Create the on NYT API
        Call<List<TopStories>> call = nytService.getTopStories(section);
        //Start the call
        call.enqueue(new Callback<List<TopStories>>() {
            @Override
            public void onResponse(Call<List<TopStories>> call, Response<List<TopStories>> response) {
                //Call the callback used in controller (TopStoriesFragment)
                if (callbacksWeakReference.get() !=null) callbacksWeakReference.get().onResponseTopStories(response.body());
            }

            @Override
            public void onFailure(Call<List<TopStories>> call, Throwable t) {
                //Call the proper callback used in controller (TopStoriesFragment)
                if (callbacksWeakReference.get() !=null) callbacksWeakReference.get().onFailureTopStories();
            }
        });
    }
    //Start to fetch MostPopular
    public static void fetchMostPopular (Callbacks callbacks, String section) {
        //create a weak reference to callback
        final WeakReference<Callbacks> callbacksWeakReference = new WeakReference<Callbacks> (callbacks);
        //Get retrofit instance and the related endpoints
        NYTService nytService1 = NYTService.retrofit.create(NYTService.class);
        //Create the on NYT API
        Call<List<MostPopular>> call = nytService1.getMostPopular(section);
        //Start the call
        call.enqueue(new Callback<List<MostPopular>>() {
            @Override
            public void onResponse(Call<List<MostPopular>> call, Response<List<MostPopular>> response) {
                //Call the callback used in controller (TopStoriesFragment)
                if (callbacksWeakReference.get() !=null) callbacksWeakReference.get().onResponseMostPopular(response.body());
            }

            @Override
            public void onFailure(Call<List<MostPopular>> call, Throwable t) {
                //Call the proper callback used in controller (TopStoriesFragment)
                if (callbacksWeakReference.get() !=null) callbacksWeakReference.get().onFailureMostPopular();
            }
        });
    }
}
