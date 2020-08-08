package com.hfut.mydesign.controller;


import com.hfut.mydesign.entity.Song;
import com.hfut.mydesign.service.SongService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import java.lang.reflect.Method;
import java.util.List;

@Controller
public class SongPageController {

    @Autowired
    SongService songService;
    public static int pageNum = 0;

    /**
     * 根据url中page值，确定pageNum的改变方式，实现分页查询
     *
     * @param model
     * @param page
     * @return
     */

    @RequestMapping(value = "/song/{page}", method = RequestMethod.GET)
    public String song(Model model, @PathVariable(value = "page") String page) {
        if (page.equals("prePage")) {
            pageNum = (pageNum - 1 < 0) ? 0 : (pageNum - 1);
        } else if (page.equals("nextPage")){
            pageNum ++;
        } else if (page.equals("firstPage")) {
            pageNum = 0;
        } else if(page.equals("currentPage")) {
            pageNum = pageNum + 0;
        }
        Page<Song> songPage = songService.queryFiftySongs(pageNum);

        model.addAttribute("songs", songPage);
        return "song";
    }
}
