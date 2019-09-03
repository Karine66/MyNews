package com.karine.mynews.Utils;

import android.support.annotation.Nullable;

import com.karine.mynews.models.TopStories;

import java.lang.ref.WeakReference;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.karine.mynews.Utils.NYTRetrofitObject.retrofit;

/**
 * Created by <Karine> on <DATE-DU-JOUR>.
 */
public class NYTCalls {

    // Create callback
    public interface Callbacks {
        void onResponseTopStories(@Nullable List<TopStories> section);
        void onFailureTopStories();
    }
    // fetch TopStories
    public static void fetchTopStories(Callbacks callbacks, String section){
        // Create a weak reference to callback
        final WeakReference<Callbacks> callbacksWeakReference = new WeakReference<Callbacks>(callbacks);
        // Get a Retrofit instance
        NYTService mNYTService = retrofit.create(NYTService.class);
        // Create the call NYT API
        Call<List<TopStories>> call = mNYTService.getTopStories(section);
        // Start the call
        call.enqueue(new Callback<List<TopStories>>() {
            @Override
            public void onResponse(Call<List<TopStories>> call, Response<List<TopStories>> response) {
                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onResponseTopStories(response.body());
            }
            @Override
            public void onFailure(Call<List<TopStories>> call, Throwable t) {
                // Call the proper callback used in controller (MainFragment)
                if (callbacksWeakReference.get() != null) callbacksWeakReference.get().onFailureTopStories();
            }
        });
    }
}

