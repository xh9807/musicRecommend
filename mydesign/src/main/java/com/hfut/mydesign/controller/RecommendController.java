package com.hfut.mydesign.controller;

import com.hfut.mydesign.entity.Recommend;
import com.hfut.mydesign.entity.Song;
import com.hfut.mydesign.service.CollectionService;
import com.hfut.mydesign.service.RecommendService;
import com.hfut.mydesign.service.SongService;
import com.hfut.mydesign.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

@Controller
public class RecommendController {

    @Autowired
    CollectionService collectionService;
    @Autowired
    SongService songService;
    @Autowired
    RecommendService recommendService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/recommend", method = RequestMethod.GET)
    public String recommend(ModelMap modelMap) {
        // 当前用户收藏歌曲数少于5首，则进行推荐被收藏次数最多的8首歌曲
        if (recommendService.isColdStart()) {
            List<Song> topSongs = songService.getTop8CollectedSongs();
            modelMap.addAttribute("recommendSongs", topSongs);
            modelMap.addAttribute("recommendType", 0);
            System.out.println("收藏冷启动结束");
        }
        // 收藏数目足够，无需冷启动，进行推荐
        else {
            List<Integer> list = new LinkedList<>();
            // 初始化相似度
            double similarity = 0;
            List<Integer> otherUsersIdList = recommendService.initOtherUsers(list);
            // 最相似的用户，默认为第一个
            Integer mostSimilarUserId = otherUsersIdList.get(0);
            // 当前用户的收藏列表
            List myCollectionSongs = recommendService.initMyCollectionSongsId();
            recommendService.initMyCollectionSongsNum();
            System.out.println("我的收藏歌曲：");
            for (int i = 0; i < myCollectionSongs.size(); i++) {
                System.out.print(myCollectionSongs.get(i) + "  ");
            }
            System.out.println();
            HashMap<Integer, Double> userSimilarity = recommendService.getUserSimilarity();
            for (Integer key : userSimilarity.keySet()) {
                if (userSimilarity.get(key) >= similarity) {
                    similarity = userSimilarity.get(key);
                    mostSimilarUserId = key;
                }
                System.out.println("用户id: " + key + ";   与当前用户相似度: " + userSimilarity.get(key));
            }
            System.out.println("相似度最高的用户id为：" + mostSimilarUserId + ",相似度为：" + similarity);
            // 过滤后得到的歌曲集合
            List<Song> recommendSongs = new LinkedList<>();
            // 所有用户与当前用户相似度为0，则进行初始推荐
            if (similarity == 0) {
                recommendSongs = songService.getTop8CollectedSongs();
                modelMap.addAttribute("recommendSongs", recommendSongs);
                modelMap.addAttribute("recommendType", 0);
            } else {
                // 要进行推荐的歌曲id集合
                List<Integer> recommendSongsId = collectionService.getAllCollectionBySongId(mostSimilarUserId);
                // 过滤recommendSongsId中当前用户已收藏的歌曲
                for (Integer songid : recommendSongsId) {
                    if (!myCollectionSongs.contains(songid)) {
                        Song collectionSong = songService.getSongBySongId(songid);
                        if (!recommendService.isExistRecommend(collectionSong.getId())) {
                            // 保存本次推荐记录
                            recommendService.savaRecommend(new Recommend(userService.currentUser.getId(),
                                    collectionSong.getId(), collectionSong.getSinger(), collectionSong.getSongName()));
                        }
                        recommendSongs.add(collectionSong);
                    }
                }
                modelMap.addAttribute("recommendSongs", recommendSongs);
                modelMap.addAttribute("recommendType", 1);
                System.out.println("-----推荐结束-----");
            }
        }
        return "recommend";
    }
}
