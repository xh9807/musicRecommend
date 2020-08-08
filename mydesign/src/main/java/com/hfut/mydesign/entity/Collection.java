package com.hfut.mydesign.entity;

import org.hibernate.annotations.GenerationTime;
import org.springframework.data.annotation.CreatedDate;

import javax.annotation.Generated;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import static org.hibernate.annotations.GenerationTime.*;

@Entity
@Table(name = "collection")
public class Collection implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer collectionId;

    @Column(name = "songId")
    private Integer songId;
    @Column(name = "userId")
    private Integer userId;
    @Column(name = "songName")
    private String songName;
    @Column(name = "singerName")
    private String singer;
    @Column(name = "createTime")
    private String createTime;

    /**
     * 含参构造方法
     * @param songId
     * @param userId
     * @param songName
     * @param singer
     * @param createTime
     */
    public Collection(Integer songId, Integer userId, String songName, String singer, String createTime) {
        this.songId = songId;
        this.userId = userId;
        this.songName = songName;
        this.singer = singer;
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Collection{" +
                "collectionId=" + collectionId +
                ", songId=" + songId +
                ", userId=" + userId +
                ", songName='" + songName + '\'' +
                ", singer='" + singer + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }

    /**
     * 无参构造
     */
    public Collection() {
    }

    public Integer getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.collectionId = collectionId;
    }

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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

}
