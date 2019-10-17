package com.karine.mynews.models;

/**
 * Created by <Karine> on <DATE-DU-JOUR>.
 */
public class NYTArticle {

    private String mPublishedDate;
    private String mSection;
    private String mSubsection;
    private String mTitle;
    private String mUrl;
    private String mMultimediaURL;




    public NYTArticle (String publishedDate, String section, String title, String url) {
        mPublishedDate = publishedDate;
        mSection = section;
        mTitle = title;
        mUrl = url;
        mMultimediaURL = null;
    }

    public String getPublishedDate() {
        return mPublishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        mPublishedDate = publishedDate;
    }

    public String getSection() {
        return mSection;
    }
    public void setSection(String section) {
        mSection = section;
    }

    public String getSubsection() {
        return mSubsection;
    }

    public void setSubsection(String subsection) {
        mSubsection = subsection;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getMultimediaURL() {
        return mMultimediaURL;
    }

    public void setMultimediaURL(String multimediaURL) {
        mMultimediaURL = multimediaURL;
    }


}


