package com.hfut.mydesign.entity;

import javax.persistence.*;

@Entity
@Table(name = "recommend")
public class Recommend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recommendId;

    @Column(name = "userId")
    private Integer userId;

    @Column(name = "songId")
    private Integer songId;

    @Column(name = "singer")
    private String singer;

    @Column(name = "songName")
    private String songName;

    public Recommend() {
    }

    public Recommend(Integer userId, Integer songId, String singer, String songName) {
        this.userId = userId;
        this.songId = songId;
        this.singer = singer;
        this.songName = songName;
    }

    public Integer getRecommendId() {
        return recommendId;
    }

    public void setRecommendId(Integer recommendId) {
        this.recommendId = recommendId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    @Override
    public String toString() {
        return "Recommend{" +
                "recommendId=" + recommendId +
                ", userId=" + userId +
                ", songId=" + songId +
                ", singer='" + singer + '\'' +
                ", songName='" + songName + '\'' +
                '}';
    }
}
