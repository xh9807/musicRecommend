package com.hfut.mydesign.repository;

import com.hfut.mydesign.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SearchRepository extends JpaRepository<Song,Integer> {
    /**
     * 通过歌名进行模糊搜索
     * @param songName
     * @return 歌曲集合
     */
    List<Song> findBySongNameLike(String songName);
}
