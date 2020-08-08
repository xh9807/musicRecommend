package com.hfut.mydesign.service;

import com.hfut.mydesign.entity.Song;
import com.hfut.mydesign.entity.SongsField;
import com.hfut.mydesign.repository.SearchRepository;
import com.hfut.mydesign.repository.SongRepository;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Service
public class SearchService {
    @Autowired
    SearchRepository searchRepository;
    @Autowired
    SolrClient solrClient;

    /**
     * 查找歌曲
     * @param songName
     * @return 歌曲集合
     */
    @Cacheable(cacheNames = {"search"},keyGenerator = "myKeyGenerator")
    public List<Song> searchSongs(String songName) {
        List<Song> songs = searchRepository.findBySongNameLike("%"+songName+"%");
        System.out.println("数据库查找测试");
        return songs;
    }

    /**
     * 在Solr中搜索匹配歌曲
     * @param songName
     * @return 歌曲列表
     * @throws IOException
     * @throws SolrServerException
     */
    public List<SongsField> getSongsFieldBySongName(String songName) throws IOException, SolrServerException {
        // 创建Solr查询
        SolrQuery solrQuery = new SolrQuery();
        // 拼接查询条件
        StringBuilder searchName = new StringBuilder("title:");
        searchName.append(songName);

        // 进行查找条件配置
        solrQuery.setQuery(searchName.toString());
        solrQuery.setStart(0);
        solrQuery.setRows(10);

        QueryResponse queryResponse = solrClient.query("songs", solrQuery);
        // 得到实体类型的List
        List<SongsField> songsFieldResult = queryResponse.getBeans(SongsField.class);
        return songsFieldResult;
    }
}
