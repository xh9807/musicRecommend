package com.hfut.mydesign.repository;

import com.hfut.mydesign.entity.Collection;
import com.hfut.mydesign.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CollectionRepository extends JpaRepository<Collection,Integer> {
    /**
     * 通过歌曲id查找记录
     * @param userId
     * @param songId
     * @return 收藏记录
     */
    Collection findByUserIdAndSongId(Integer userId,Integer songId);

    /**
     * 查找当前用户收藏的歌曲
     * @param id
     * @return 收藏列表
     */
    List<Collection> findByUserId(Integer id);

    /**
     * 通过歌曲名和歌手信息删除收藏记录
     * @param songName
     * @param singer
     */
    @Transactional
    void deleteBySongNameAndSinger(String songName,String singer);

    /**
     * 查询该用户的收藏歌曲数
     * @param userId
     * @return
     */
    int countCollectionByUserId(Integer userId);

    /**
     * 通过用户id获取此用户所有收藏歌曲的songId
     * @param userId
     * @return
     */
    @Query("select songId from Collection where userId=?1")
    List<Integer> findAllSongId(Integer userId);
}
