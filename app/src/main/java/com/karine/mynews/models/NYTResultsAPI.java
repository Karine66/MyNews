package com.karine.mynews.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.karine.mynews.models.MostPopularAPI.Medium;
import com.karine.mynews.models.TopStoriesAPI.Multimedium;
import com.karine.mynews.models.TopStoriesAPI.Result;


import java.util.List;

/**
 * Created by <Karine> on <DATE-DU-JOUR>.
 */
public class NYTResultsAPI {
    @SerializedName("results")
    @Expose
    private List<Result> mResults =null;
    public  List<Result> getResults() {
        return mResults;
    }
}
