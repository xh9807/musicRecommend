package com.hfut.mydesign.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SingerPageController {
    /**
     * 接收singer映射，转发页面
     * @return
     */
    @GetMapping(value = "/singer")
    public String singer() {
        return "singer";
    }
}
