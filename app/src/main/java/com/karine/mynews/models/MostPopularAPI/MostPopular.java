
package com.karine.mynews.models.MostPopularAPI;

import java.util.List;
import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class MostPopular {

    @SerializedName("copyright")
    private String mCopyright;
    @SerializedName("num_results")
    private Long mNumResults;
    @SerializedName("results")
    private List<Result> mResults;
    @SerializedName("status")
    private String mStatus;

    public String getCopyright() {
        return mCopyright;
    }

    public void setCopyright(String copyright) {
        mCopyright = copyright;
    }

    public Long getNumResults() {
        return mNumResults;
    }

    public void setNumResults(Long numResults) {
        mNumResults = numResults;
    }

    public List<Result> getResults() {
        return mResults;
    }

    public void setResults(List<Result> results) {
        mResults = results;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

}
