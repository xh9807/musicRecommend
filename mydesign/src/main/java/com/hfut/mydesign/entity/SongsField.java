package com.hfut.mydesign.entity;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

// 映射solr核心
@SolrDocument(solrCoreName = "songs")
public class SongsField {
    @Field
    private String id;
    @Field("title")
    private String songName;
    @Field
    private String singer;
    @Field
    private String attrsPubdate;
    @Field
    private String attrsMedia;
    @Field
    private String musicURL;

    public SongsField(String id, String songName, String singer, String attrsPubdate, String attrsMedia, String musicURL) {
        this.id = id;
        this.songName = songName;
        this.singer = singer;
        this.attrsPubdate = attrsPubdate;
        this.attrsMedia = attrsMedia;
        this.musicURL = musicURL;
    }

    public SongsField() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getAttrsPubdate() {
        return attrsPubdate;
    }

    public void setAttrsPubdate(String attrsPubdate) {
        this.attrsPubdate = attrsPubdate;
    }

    public String getAttrsMedia() {
        return attrsMedia;
    }

    public void setAttrsMedia(String attrsMedia) {
        this.attrsMedia = attrsMedia;
    }

    public String getMusicURL() {
        return musicURL;
    }

    public void setMusicURL(String musicURL) {
        this.musicURL = musicURL;
    }

    @Override
    public String toString() {
        return "SongsField{" +
                "id='" + id + '\'' +
                ", songName='" + songName + '\'' +
                ", singer='" + singer + '\'' +
                ", attrsPubdate='" + attrsPubdate + '\'' +
                ", attrsMedia='" + attrsMedia + '\'' +
                ", musicURL='" + musicURL + '\'' +
                '}';
    }
}
