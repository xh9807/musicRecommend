package com.hfut.mydesign.service;

import com.hfut.mydesign.entity.User;
import com.hfut.mydesign.repository.UserRepository;
import org.apache.tomcat.util.http.parser.Cookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    //保存当前登录用户
    public User currentUser;

    /**
     * 判断登录用户是否存在
     * @param name
     * @param password
     * @return
     */
    public Boolean UserIsNull(String name,String password) {
        User user = userRepository.findUserByNameAndPassword(name,password);
        currentUser = user;
        return (user == null);
    }

    public void saveAll(List<User> user){
        userRepository.saveAll(user);
    }

    public List<User> getAllUser() {
        List<User> userList = userRepository.findAll();
        return userList;
    }
}
