package com.hfut.mydesign.controller;

import com.hfut.mydesign.entity.Collection;
import com.hfut.mydesign.entity.Song;
import com.hfut.mydesign.service.CollectionService;
import com.hfut.mydesign.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
public class CollectionController {

    @Autowired
    CollectionService collectionService;
    @Autowired
    SongService songService;

    /**
     * 收藏歌曲
     * @param songName
     * @param singer
     */
    @GetMapping(value = "/mycollect/{songName}&{singer}")
    public String mycollect(@PathVariable("songName") String songName,@PathVariable("singer") String singer) {
        Song collectionSong = songService.getSongIdBySongNameAndSinger(songName,singer);
        Integer collectionSongId = collectionSong.getId();
        Integer currentUserId = collectionService.getCurrentUserId();
        // 获取当前时间
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String currentTime = sdf.format(date);
//        System.out.println("现在时间：" + sdf.format(date)); // 输出已经格式化的现在时间（24小时制）

        //查找相应的收藏记录
        Boolean existFlag = collectionService.isExistCollection(currentUserId,collectionSongId);

        //不存在相应记录，则添加进收藏列表
        if(!existFlag) {
            //创建收藏对象
            Collection collection = new Collection(collectionSongId,currentUserId,songName,singer,currentTime);
            collectionService.save(collection);
            //该歌曲被收藏次数+1
            songService.updateCollectTime(collectionSongId);
        }
        //获取当前正在浏览的页码，重定向回此页面
//        Integer pageNum = songService.pageNum;
        return "redirect:/song/currentPage";
    }
}
