
package com.karine.mynews.models.SearchAPI;

import com.google.gson.annotations.SerializedName;


@SuppressWarnings("unused")
public class Headline {

    @SerializedName("content_kicker")
    private Object mContentKicker;
    @SerializedName("kicker")
    private Object mKicker;
    @SerializedName("main")
    private String mMain;
    @SerializedName("name")
    private Object mName;
    @SerializedName("print_headline")
    private Object mPrintHeadline;
    @SerializedName("seo")
    private Object mSeo;
    @SerializedName("sub")
    private Object mSub;

    public Object getContentKicker() {
        return mContentKicker;
    }

    public void setContentKicker(Object contentKicker) {
        mContentKicker = contentKicker;
    }

    public Object getKicker() {
        return mKicker;
    }

    public void setKicker(Object kicker) {
        mKicker = kicker;
    }

    public String getMain() {
        return mMain;
    }

    public void setMain(String main) {
        mMain = main;
    }

    public Object getName() {
        return mName;
    }

    public void setName(Object name) {
        mName = name;
    }

    public Object getPrintHeadline() {
        return mPrintHeadline;
    }

    public void setPrintHeadline(Object printHeadline) {
        mPrintHeadline = printHeadline;
    }

    public Object getSeo() {
        return mSeo;
    }

    public void setSeo(Object seo) {
        mSeo = seo;
    }

    public Object getSub() {
        return mSub;
    }

    public void setSub(Object sub) {
        mSub = sub;
    }

}
