
package com.karine.mynews.models.TopStoriesAPI;

import java.util.List;

import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class TopStories {

    @SerializedName("copyright")
    private String mCopyright;
    @SerializedName("last_updated")
    private String mLastUpdated;
    @SerializedName("num_results")
    private Long mNumResults;
    @SerializedName("results")
    private List<Result> mResults;
    @SerializedName("section")
    private String mSection;
    @SerializedName("status")
    private String mStatus;

    public String getCopyright() {
        return mCopyright;
    }

    public void setCopyright(String copyright) {
        mCopyright = copyright;
    }

    public String getLastUpdated() {
        return mLastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        mLastUpdated = lastUpdated;
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

    public String getSection() {
        return mSection;
    }

    public void setSection(String section) {
        mSection = section;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

}
