package com.hfut.mydesign.controller;


import com.hfut.mydesign.entity.Collection;
import com.hfut.mydesign.entity.Song;
import com.hfut.mydesign.entity.SongsField;
import com.hfut.mydesign.service.CollectionService;
import com.hfut.mydesign.service.SearchService;
import com.hfut.mydesign.service.SongService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class SearchController {
    @Autowired
    SearchService searchService;
    @Autowired
    CollectionService collectionService;
    @Autowired
    SongService songService;

    /**
     * 在solr中获取歌曲搜索结果
     * @param modelMap
     * @param searchName
     * @return 歌曲列表
     * @throws IOException
     * @throws SolrServerException
     */
    @GetMapping(value = "/solrSearch")
    public String searchSongsField(ModelMap modelMap,@RequestParam("searchName") String searchName) throws IOException, SolrServerException {
        List<SongsField> searchResult = searchService.getSongsFieldBySongName(searchName);
        if(searchResult.isEmpty()){
            modelMap.addAttribute("emptyWarning","未查询到歌曲");
        } else {
            modelMap.addAttribute("searchSongs",searchResult);
        }
        return "search";
    }

    /**
     * 搜索结果加入收藏
     * @param songName
     * @param singer
     * @return
     */
    @GetMapping(value = "/tocollection/{songName}&{singer}")
    public String mycollect(@PathVariable("songName") String songName, @PathVariable("singer") String singer) {
        Song collectionSong = songService.getSongIdBySongNameAndSinger(songName,singer);
        Integer collectionSongId = collectionSong.getId();
        Integer collectedTime = collectionSong.getCollectedTime();
        Integer currentUserId = collectionService.getCurrentUserId();
        // 获取当前时间
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String currentTime = sdf.format(date);

        //查找相应的收藏记录
        Boolean existFlag = collectionService.isExistCollection(currentUserId,collectionSongId);
        if(!existFlag){
            //创建收藏对象
            Collection collection = new Collection(collectionSongId,currentUserId,songName,singer,currentTime);
            collectionService.save(collection);
            //歌曲被收藏次数+1
            songService.updateCollectTime(collection.getSongId());
        }
        return "redirect:/collection";
    }
}
