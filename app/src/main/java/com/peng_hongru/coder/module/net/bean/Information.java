package com.peng_hongru.coder.module.net.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;

import java.util.List;

@Entity
public class Information implements Parcelable {
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
    /*@Id(autoincrement = true)
    private long id;*/

    @Property(nameInDb = "infor_id")
    private String _id;

    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;
    @Transient
    private List<String> images;




    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
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

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
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

    public Information() {
    }


   /* public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }*/

    public boolean getUsed() {
        return this.used;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._id);
        dest.writeString(this.createdAt);
        dest.writeString(this.desc);
        dest.writeString(this.publishedAt);
        dest.writeString(this.source);
        dest.writeString(this.type);
        dest.writeString(this.url);
        dest.writeByte(this.used ? (byte) 1 : (byte) 0);
        dest.writeString(this.who);
        dest.writeStringList(this.images);
    }

    protected Information(Parcel in) {
        this._id = in.readString();
        this.createdAt = in.readString();
        this.desc = in.readString();
        this.publishedAt = in.readString();
        this.source = in.readString();
        this.type = in.readString();
        this.url = in.readString();
        this.used = in.readByte() != 0;
        this.who = in.readString();
        this.images = in.createStringArrayList();
    }

    @Generated(hash = 1986882726)
    public Information(String _id, String createdAt, String desc, String publishedAt, String source, String type,
            String url, boolean used, String who) {
        this._id = _id;
        this.createdAt = createdAt;
        this.desc = desc;
        this.publishedAt = publishedAt;
        this.source = source;
        this.type = type;
        this.url = url;
        this.used = used;
        this.who = who;
    }

    public static final Parcelable.Creator<Information> CREATOR = new Parcelable.Creator<Information>() {
        @Override
        public Information createFromParcel(Parcel source) {
            return new Information(source);
        }

        @Override
        public Information[] newArray(int size) {
            return new Information[size];
        }
    };
}