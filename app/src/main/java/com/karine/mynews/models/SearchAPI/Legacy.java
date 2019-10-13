
package com.karine.mynews.models.SearchAPI;

import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class Legacy {

    @SerializedName("wide")
    private String mWide;
    @SerializedName("wideheight")
    private Long mWideheight;
    @SerializedName("widewidth")
    private Long mWidewidth;

    public String getWide() {
        return mWide;
    }

    public void setWide(String wide) {
        mWide = wide;
    }

    public Long getWideheight() {
        return mWideheight;
    }

    public void setWideheight(Long wideheight) {
        mWideheight = wideheight;
    }

    public Long getWidewidth() {
        return mWidewidth;
    }

    public void setWidewidth(Long widewidth) {
        mWidewidth = widewidth;
    }

}
