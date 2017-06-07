package com.laulee.retrofit2.bean;

/**
 * Created by laulee on 17/2/28.
 */

public class GankItemEntity {

    /**
     * _id : 58b2a8fb421aa90efc4fb612
     * createdAt : 2017-02-26T18:07:55.992Z
     * desc : Shuttle音乐播放器
     * publishedAt : 2017-02-27T11:31:40.141Z
     * source : chrome
     * type : Android
     * url : https://github.com/timusus/Shuttle
     * used : true
     * who : Jason
     */

    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;

    public String get_id() { return _id;}

    public void set_id( String _id ) { this._id = _id;}

    public String getCreatedAt() { return createdAt;}

    public void setCreatedAt( String createdAt ) { this.createdAt = createdAt;}

    public String getDesc() { return desc;}

    public void setDesc( String desc ) { this.desc = desc;}

    public String getPublishedAt() { return publishedAt;}

    public void setPublishedAt( String publishedAt ) { this.publishedAt = publishedAt;}

    public String getSource() { return source;}

    public void setSource( String source ) { this.source = source;}

    public String getType() { return type;}

    public void setType( String type ) { this.type = type;}

    public String getUrl() { return url;}

    public void setUrl( String url ) { this.url = url;}

    public boolean isUsed() { return used;}

    public void setUsed( boolean used ) { this.used = used;}

    public String getWho() { return who;}

    public void setWho( String who ) { this.who = who;}
}
