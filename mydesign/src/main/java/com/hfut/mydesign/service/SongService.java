package com.hfut.mydesign.service;

import com.hfut.mydesign.entity.Song;
import com.hfut.mydesign.repository.SongRepository;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author xuhao
 * @version 2.1
 */
@Service
public class SongService {
    @Autowired
    SongRepository songRepository;
    /**
     * start查询页面，每页显示50条数据
     * @param page
     * @return 分页数据
     */
    public Page<Song> queryFiftySongs(int page) {
        page = (page < 0) ? 0 : page;
        PageRequest pageRequest = PageRequest.of(page, 50);
        return songRepository.findAll(pageRequest);
    }

    /**
     * 通过歌曲名和歌手获取歌曲
     * @param songName
     * @param singer
     * @return song
     */
    @Cacheable(cacheNames = "getSong",keyGenerator = "myKeyGenerator")
    public Song getSongIdBySongNameAndSinger(String songName, String singer) {
        Song songResult = songRepository.findBySongNameAndSinger(songName, singer);
        return songResult;
    }

    /**
     * 根据歌曲的id，更新歌曲被收藏的次数
     * @param songId
     */
    public void updateCollectTime(Integer songId){
        songRepository.updateSongCollectedTime(songId);
    }

    /**
     * 选择被收藏次数最多的8首推荐
     * @return
     */
    public List<Song> getTop8CollectedSongs() {
        List<Song> top = songRepository.findTop8ByOrderByCollectedTimeDesc();
        return top;
    }

    public Song getSongBySongId(Integer id) {
        Song song = songRepository.getOne(id);
        return song;
    }
}
