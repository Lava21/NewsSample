package com.mas.samplenews.model;

import java.io.Serializable;

public class NewsModel implements Serializable {
    private String idNews, titleNews, publishedATNews, urlNews, urlToImageNews;

    public String getIdNews() {
        return idNews;
    }

    public void setIdNews(String idNews) {
        this.idNews = idNews;
    }

    public String getTitleNews() {
        return titleNews;
    }

    public void setTitleNews(String titleNews) {
        this.titleNews = titleNews;
    }

    public String getPublishedATNews() {
        return publishedATNews;
    }

    public void setPublishedATNews(String publishedATNews) {
        this.publishedATNews = publishedATNews;
    }

    public String getUrlNews() {
        return urlNews;
    }

    public void setUrlNews(String urlNews) {
        this.urlNews = urlNews;
    }

    public String getUrlToImageNews() {
        return urlToImageNews;
    }

    public void setUrlToImageNews(String urlToImageNews) {
        this.urlToImageNews = urlToImageNews;
    }
}
