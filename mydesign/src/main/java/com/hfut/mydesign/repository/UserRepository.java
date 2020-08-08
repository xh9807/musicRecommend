package com.hfut.mydesign.repository;

import com.hfut.mydesign.entity.User;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findUserByNameAndPassword(String name,String password);

    List<User> findAll();
}
