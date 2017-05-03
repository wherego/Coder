package com.peng_hongru.coder.module.dao.bean;

import com.peng_hongru.coder.module.net.bean.Information;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;


public class Collection implements Serializable {
    /**
     * _id : 58fddc9a421aa954511ebf00
     * createdAt : 2017-04-24T19:08:10.208Z
     * desc : 横幅广告轮播控件
     * images : ["http://img.gank.io/ffa66550-643a-429c-8e4c-53b51827a637"]
     * publishedAt : 2017-04-25T13:11:39.357Z
     * source : web
     * type : Android
     * url : https://github.com/czy1121/bannerview
     * used : true
     * who : ezy
     */
    protected String saveAt;
    protected String desc;
    protected String source;
    protected String type;
    protected String url;
    protected String who;
    protected List<String> images;
    protected boolean used;
    protected String publishedAt;
    protected String createdAt;

    public Collection() {

    }

    public Collection(Information information) {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd   hh:mm:ss");
        saveAt = sDateFormat.format(new java.util.Date());
        desc = information.getDesc();
        source = information.getSource();
        type = information.getType();
        url = information.getUrl();
        who = information.getWho();
        images = information.getImages();
        used = information.isUsed();
        createdAt = information.getCreatedAt();
        publishedAt = information.getPublishedAt();
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }


    public String getSaveAt() {
        return saveAt;
    }

    public void setSaveAt(String saveAt) {
        this.saveAt = saveAt;
    }
}