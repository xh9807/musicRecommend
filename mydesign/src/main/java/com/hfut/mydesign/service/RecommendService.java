package com.hfut.mydesign.service;

import ch.qos.logback.classic.turbo.ReconfigureOnChangeFilter;
import com.hfut.mydesign.entity.Recommend;
import com.hfut.mydesign.entity.User;
import com.hfut.mydesign.repository.RecommendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class RecommendService {
    @Autowired
    RecommendRepository recommendRepository;
    @Autowired
    UserService userService;
    @Autowired
    CollectionService collectionService;
    @Autowired
    SongService songService;

    // 其他用户id集合
    private List<Integer> otherUsersId;
    // 当前用户收藏歌曲的songId
    private List<Integer> myCollectionSongsId;
    // 当前用户收藏歌曲数目
    private Integer myCollectionSongsNum;

    // 判断是否进行推荐冷启动
    public boolean isColdStart() {
        if (collectionService.getUserCollectionNum(userService.currentUser.getId()) <= 5) {
            return true;
        } else {
            return false;
        }
    }

    // 初始化其他用户列表
//    @Cacheable(cacheNames = "initOther",keyGenerator = "myKeyGenerator")
    public List initOtherUsers(List<Integer> otherUsersId) {
        List<User> allUser = userService.getAllUser();
        for (int i = 0; i < allUser.size(); i++) {
            User user = allUser.get(i);
            if (user.getId().intValue() != userService.currentUser.getId().intValue()) {
                otherUsersId.add(user.getId());
            }
        }
        this.otherUsersId = otherUsersId;
//        System.out.println("初始化其他用户");
        return otherUsersId;
    }

    //初始化我的收藏歌曲id列表
    public List initMyCollectionSongsId() {
        myCollectionSongsId = collectionService.getAllCollectionBySongId(userService.currentUser.getId());
        return myCollectionSongsId;
    }

    //初始化我的收藏数
    public void initMyCollectionSongsNum() {
        myCollectionSongsNum = collectionService.getUserCollectionNum(userService.currentUser.getId());
        System.out.println("我的收藏数：" + myCollectionSongsNum);
    }

    // 计算用户之间的余弦相似度
    public HashMap getUserSimilarity() {
        List<Integer> othercollectionSongsId;
        HashMap<Integer, Double> hashMap = new HashMap<>();
        // 遍历得到其他用户收藏列表
        Integer userId;
        for (int i = 0; i < otherUsersId.size(); i++) {
            userId = otherUsersId.get(i);
            othercollectionSongsId = collectionService.getAllCollectionBySongId(userId);
            // 其他用户收藏歌曲数目
            int otherSongsNum = othercollectionSongsId.size();
            int mySongsNum = collectionService.getUserCollectionNum(userService.currentUser.getId());
            double sameNum = 0;   //相同收藏的个数,即余弦相似度分子
            double cosSimilarity; //余弦相似度

            // 余弦相似度计算分母
            double denominator = Math.sqrt(((double) otherSongsNum) * ((double) mySongsNum));
            for (Integer songId : myCollectionSongsId) {
                if (othercollectionSongsId.contains(songId)) {
                    sameNum++;
                }
            }
            // 计算用户余弦相似度
            if (denominator != 0) {
                cosSimilarity = (sameNum / denominator);
                hashMap.put(userId, cosSimilarity);
            } else {
                cosSimilarity = 0;
                hashMap.put(userId, cosSimilarity);
            }
        }
        return hashMap;
    }

    // 删除当前用户的推荐记录
    public void deleteRecommend() {
        recommendRepository.deleteByUserId(userService.currentUser.getId());
    }

    // 保存推荐记录
    public void savaRecommend(Recommend recommend) {
        recommendRepository.save(recommend);
    }

    // 是否已经存在记录
    public boolean isExistRecommend(Integer songId) {
        if (recommendRepository.findBySongId(songId) != null) {
            return true;
        } else {
            return false;
        }
    }
}
