package com.hfut.mydesign.repository;

import com.hfut.mydesign.entity.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
@Repository
public interface SongRepository extends JpaRepository<Song,Integer> {

    /**
     * SpringJpa分页查询
     * @param pageable
     * @return
     */
    Page<Song> findAll(Pageable pageable);

    Song findBySongNameAndSinger(String songName,String singer);

    @Transactional
    @Modifying
    @Query("update Song s set s.collectedTime = s.collectedTime+1 where s.id = ?1")
    void updateSongCollectedTime(Integer songId);

    List<Song> findTop8ByOrderByCollectedTimeDesc();

//    Song findById(Integer id);
}

