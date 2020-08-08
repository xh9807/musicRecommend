package com.hfut.mydesign;

import ch.qos.logback.classic.turbo.ReconfigureOnChangeFilter;
import com.hfut.mydesign.entity.Collection;
import com.hfut.mydesign.entity.Song;
import com.hfut.mydesign.entity.SongsField;
import com.hfut.mydesign.entity.SongsField;
import com.hfut.mydesign.entity.User;
import com.hfut.mydesign.repository.SongRepository;
import com.hfut.mydesign.service.CollectionService;
import com.hfut.mydesign.service.RecommendService;
import com.hfut.mydesign.service.UserService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MydesignApplicationTests {
    @Autowired
    SongRepository songRepository;
    @Autowired
    RecommendService recommendService;
    @Autowired
    UserService userService;
    @Autowired
    CollectionService collectionService;

    //    推荐冷启动测试
    @Test
    void contextLoads() {

        List<Song> top8ByOrderByCollectedTimeDesc = songRepository.findTop8ByOrderByCollectedTimeDesc();
        for (Song good : top8ByOrderByCollectedTimeDesc) {
            System.out.println(good);
        }
    }

    //    算法数据测试
    @Test
    void testRecommend() {
        User user;
        List<Integer> otherUsersId = new LinkedList<>();
        List<User> allUser = userService.getAllUser();
        for (int i = 0; i < allUser.size(); i++) {
            user = allUser.get(i);
            otherUsersId.add(user.getId());
            System.out.println(user);
        }
        System.out.println("---------");
        for (Integer i : otherUsersId) {
            System.out.println(i);
        }
        System.out.println("1号用户收藏歌曲");

        List<Integer> allSongId_1 = collectionService.getAllCollectionBySongId(otherUsersId.get(0));
        for (Integer integer : allSongId_1) {
            System.out.println(integer);
        }
        System.out.println("2号用户收藏歌曲");
        List<Integer> allSongId_2 = collectionService.getAllCollectionBySongId(otherUsersId.get(2));
        for (Integer integer : allSongId_2) {
            System.out.println(integer);

        }
        System.out.println("---------");
        int sum = 0;
        for (Integer integer : allSongId_1) {
            if (allSongId_2.contains(integer)) {
                sum++;
            }
        }
        System.out.println("相同的收藏歌曲数为：" + sum);
        System.out.println("----------");
    }
    private HttpSolrClient solrClient = new HttpSolrClient.Builder("http://127.0.0.1:8983/solr/").build();
    @Test

    void solrjTest() throws IOException, SolrServerException {
        SolrDocument solrDocument = solrClient.getById("songs", "1394541");
        // 文档解析器
        DocumentObjectBinder binder = solrClient.getBinder();
        SongsField bean = binder.getBean(SongsField.class, solrDocument);
        System.out.println(bean);
    }

    // solr模糊搜索测试
    @Test
    void solrjQueryTest() throws IOException, SolrServerException {
        SolrQuery solrQuery = new SolrQuery();
        StringBuilder stringBuilder = new StringBuilder("title:");
        System.out.println(stringBuilder);
        String i = "好像在一起";
        stringBuilder.append(i);
        System.out.println(stringBuilder);
        solrQuery.setQuery(stringBuilder.toString());
//        solrQuery.setStart(0);
//        solrQuery.setRows(5);
        QueryResponse query = solrClient.query("songs", solrQuery);

        List<SongsField> list = query.getBeans(SongsField.class);
        for(SongsField songs:list) {
            System.out.println(songs);
        }
    }

    @Test
    void calc () {
        int a,b;
        a = 2;
        b = 3;
        System.out.println("result:"+(a+b));
    }
}
