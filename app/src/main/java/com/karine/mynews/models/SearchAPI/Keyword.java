
package com.karine.mynews.models.SearchAPI;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Keyword {

    @SerializedName("major")
    private String mMajor;
    @SerializedName("name")
    private String mName;
    @SerializedName("rank")
    private Long mRank;
    @SerializedName("value")
    private String mValue;

    public String getMajor() {
        return mMajor;
    }

    public void setMajor(String major) {
        mMajor = major;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Long getRank() {
        return mRank;
    }

    public void setRank(Long rank) {
        mRank = rank;
    }

    public String getValue() {
        return mValue;
    }

    public void setValue(String value) {
        mValue = value;
    }

}
