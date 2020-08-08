package com.hfut.mydesign.entity;


import org.springframework.stereotype.Controller;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "song_info")
public class Song implements Serializable {
    @Id
    private Integer id;
    @Column(name = "singer")
    private String singer;
    @Column(name = "image")
    private String image;
    @Column(name = "title")
    private String songName;
    @Column(name = "attrsPubdate")
    private String attrsPubdate;
    @Column(name = "attrsTitle")
    private String attrsTitle;
    @Column(name = "attrsMedia")
    private String attrsMedia;
    @Column(name = "tags")
    private String tags;
    @Column(name = "musicURL")
    private String musicURL;
    @Column(name = "collectedTime")
    private Integer collectedTime;

    public Song() {
    }

    public Song(Integer id, String singer, String image, String songName, String attrsPubdate, String attrsTitle, String attrsMedia, String tags, String musicURL, Integer collectedTime) {
        this.id = id;
        this.singer = singer;
        this.image = image;
        this.songName = songName;
        this.attrsPubdate = attrsPubdate;
        this.attrsTitle = attrsTitle;
        this.attrsMedia = attrsMedia;
        this.tags = tags;
        this.musicURL = musicURL;
        this.collectedTime = collectedTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getAttrsPubdate() {
        return attrsPubdate;
    }

    public void setAttrsPubdate(String attrsPubdate) {
        this.attrsPubdate = attrsPubdate;
    }

    public String getAttrsTitle() {
        return attrsTitle;
    }

    public void setAttrsTitle(String attrsTitle) {
        this.attrsTitle = attrsTitle;
    }

    public String getAttrsMedia() {
        return attrsMedia;
    }

    public void setAttrsMedia(String attrsMedia) {
        this.attrsMedia = attrsMedia;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getMusicURL() {
        return musicURL;
    }

    public void setMusicURL(String musicURL) {
        this.musicURL = musicURL;
    }

    public Integer getCollectedTime() {
        return collectedTime;
    }

    public void setCollectedTime(Integer collectedTime) {
        this.collectedTime = collectedTime;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", singer='" + singer + '\'' +
                ", image='" + image + '\'' +
                ", songName='" + songName + '\'' +
                ", attrsPubdate='" + attrsPubdate + '\'' +
                ", attrsTitle='" + attrsTitle + '\'' +
                ", attrsMedia='" + attrsMedia + '\'' +
                ", tags='" + tags + '\'' +
                ", musicURL='" + musicURL + '\'' +
                ", collectedTime=" + collectedTime +
                '}';
    }
}
