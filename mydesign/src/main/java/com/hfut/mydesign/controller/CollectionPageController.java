package com.hfut.mydesign.controller;


import com.hfut.mydesign.entity.Collection;
import com.hfut.mydesign.entity.Song;
import com.hfut.mydesign.service.CollectionService;
import com.hfut.mydesign.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 接收collection请求，转发到收藏页面
 */
@Controller
public class CollectionPageController {

    @Autowired
    CollectionService collectionService;
    @Autowired
    SongService songService;

    /**
     * 接收collection请求，转发页面
     * @param model
     * @return
     */
    @GetMapping(value = "/collection")
    public String collection(Model model) {
        List<Collection> allCollection = collectionService.getAllCollection();
        model.addAttribute("collections", allCollection);
        return "collection";
    }

    @GetMapping(value = "/deleteCollection/{songName}&{singer}")
    public String deleteCollection(@PathVariable("songName") String songName, @PathVariable("singer") String singer) {
        collectionService.deleteCollection(songName, singer);
        return "redirect:/collection";
    }
}
