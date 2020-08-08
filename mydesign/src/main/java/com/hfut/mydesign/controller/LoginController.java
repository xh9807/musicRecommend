package com.hfut.mydesign.controller;

import com.hfut.mydesign.entity.User;
import com.hfut.mydesign.service.RecommendService;
import com.hfut.mydesign.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    RecommendService recommendService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public  String index() {
        return "login";
    }

    /**
     * 退出登录，清空推荐记录
     * @return
     */
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(){
        recommendService.deleteRecommend();
        return "login";
    }

    @PostMapping(value = "/login")
    public String login(@RequestParam("name") String name,
                        @RequestParam("password") String password,
                        Map<String,Object> map, HttpSession session) {
        if(userService.UserIsNull(name,password)){
            map.put("msg","用户名密码错误");
            return "login";
        } else {
            session.setAttribute("loginUser",name);
            //重定向到主页,防止表单重复提交
            return "redirect:/main.html";
        }
    }

    @GetMapping(value = "/home")
    public String home() {
        return "home";
    }

    /**
     * 存储注册用户信息
     * @param Newuser 新用户名
     * @param Newpasswd  新密码
     * @param QQnum  QQ号码
     * @return
     */
//    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @PostMapping(value = "/register")
    public String register(@RequestParam("Newuser") String Newuser,
                           @RequestParam("Newpasswd") String Newpasswd,
                           @RequestParam("qq") String QQnum) {
        List<User> userList = new LinkedList<>();
        User user = new User(Newuser,Newpasswd,QQnum);
        userList.add(user);
        try{
            userService.saveAll(userList);
        } catch (Exception e){
            e.printStackTrace();
        }

        return "login";
    }


}
