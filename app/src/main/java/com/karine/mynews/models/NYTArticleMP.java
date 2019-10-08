package com.karine.mynews.models;

/**
 * Created by <Karine> on <DATE-DU-JOUR>.
 */
public class NYTArticleMP {

    private String mPublishedDate;
    private String mSection;
    private String mTitle;
    private String mUrl;
    private String mMediaURL;


    public NYTArticleMP (String publishedDate, String section, String title, String url) {
        mPublishedDate = publishedDate;
        mSection = section;
        mTitle = title;
        mUrl = url;
        mMediaURL = null;

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

    public String getMediaURL() {
        return mMediaURL;
    }

    public void setMediaURL(String mediaURL) {
        mMediaURL = mediaURL;
    }


}

}
