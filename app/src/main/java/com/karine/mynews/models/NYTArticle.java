package com.karine.mynews.models;

import com.karine.mynews.models.SearchAPI.Multimedium;
import com.karine.mynews.models.SearchAPI.Response;

import java.util.List;

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
    //For search
    private String mPubDate;
    private String mSectionName;
    private List mMultimedia;
    private String mWebUrl;



    public NYTArticle (String publishedDate, String section, String title, String url) {
        mPublishedDate = publishedDate;
        mSection = section;
        mTitle = title;
        mUrl = url;
        mMultimediaURL = null;

    }
    //For Search
    public NYTArticle(String pubDate, String sectionName, List<Multimedium> multimedia, String webUrl) {

        mPublishedDate = pubDate;
        mSection = sectionName;
        mMultimedia = multimedia;
        mUrl = webUrl;
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

    //For Search
    public String getPubDate() {
        return mPublishedDate;
    }

    public void setPubDate(String pubDate) {
        mPublishedDate = pubDate;
    }

    public String getSectionName() {
        return mSection;
    }

    public void setSectionName(String sectionName) {
        mSection = sectionName;
    }

    public List<Multimedium> getMultimedia() {
        return mMultimedia;
    }

    public void setMultimedia(List<Multimedium> multimedia) {
        mMultimedia = multimedia;
    }

    public String getWebUrl() {
        return mUrl;
    }

    public void setWebUrl(String webUrl) {
        mUrl = webUrl;
    }


}


