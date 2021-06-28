package com.example.videostreamrestapi.models;

import java.io.Serializable;

public class Video implements Serializable {
    private String title, source, uploaded;
    private String views;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Video{" +
                "title='" + title + '\'' +
                ", source='" + source + '\'' +
                ", uploaded='" + uploaded + '\'' +
                ", views='" + views + '\'' +
                ", description='" + description + '\'' +
                ", URL='" + URL + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                '}';
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    private String URL;
    private String thumbnail;

    public Video(String title, String source, String uploaded, String views, String thumbnail, String URL) {
        this.title = title;
        this.source = source;
        this.uploaded = uploaded;
        this.views = views;
        this.thumbnail = thumbnail;
        this.URL = URL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUploaded() {
        return uploaded;
    }

    public void setUploaded(String uploaded) {
        this.uploaded = uploaded;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
