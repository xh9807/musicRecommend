package com.hfut.mydesign.service;

import com.hfut.mydesign.entity.Collection;
import com.hfut.mydesign.entity.Song;
import com.hfut.mydesign.repository.CollectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service
public class CollectionService {
    @Autowired
    CollectionRepository collectionRepository;
    @Autowired
    UserService userService;

    /**
     * 根据用户id和歌曲id判断该歌曲是否被当前用户收藏
     * @param userId
     * @param songId
     * @return 判断的布尔值
     */
    public Boolean isExistCollection(Integer userId, Integer songId) {
        Collection collection = collectionRepository.findByUserIdAndSongId(userId,songId);
        if (collection == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 获取当前用户收藏的所有歌曲
     * @return 歌曲列表
     */
    public List<Collection> getAllCollection() {
        Integer id = userService.currentUser.getId();
        return collectionRepository.findByUserId(id);
    }

    /**
     * 保存需要收藏的歌曲
     * @param collectSong
     */
    public void save(Collection collectSong) {
        collectionRepository.save(collectSong);
    }

    /**
     * 获取当前登录用户的Id
     * @return id
     */
    public Integer getCurrentUserId(){
        return userService.currentUser.getId();
    }

    /**
     * 删除收藏记录
     * @param songName
     * @param singer
     */
    public  void deleteCollection(String songName,String singer) {
        collectionRepository.deleteBySongNameAndSinger(songName,singer);
    }

    /**
     * 通过用户id获取此用户收藏歌曲数目
     * @param userId
     * @return
     */
    public int getUserCollectionNum(Integer userId) {
        return collectionRepository.countCollectionByUserId(userId);
    }

    /**
     * 获取指定用户所有收藏歌曲的id
     * @param userId
     * @return
     */
    public List<Integer> getAllCollectionBySongId(Integer userId){
        List<Integer> allSongId = collectionRepository.findAllSongId(userId);
        return allSongId;
    }

}
