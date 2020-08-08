package com.hfut.mydesign.repository;

import com.hfut.mydesign.entity.Recommend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RecommendRepository extends JpaRepository<Recommend,Integer> {
    //删除推荐记录
    @Transactional
    void deleteByUserId(Integer userId);
    Recommend findBySongId(Integer songId);
}

