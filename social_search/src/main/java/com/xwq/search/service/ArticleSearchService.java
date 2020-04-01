package com.xwq.search.service;

import com.xwq.search.dao.ArticleSearchDao;
import com.xwq.search.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @Author: Xiang Wenqin
 * @DateTime: 2020/4/1 11:57
 */
@Service
public class ArticleSearchService {
    @Autowired
    private ArticleSearchDao articleSearchDao;

    /**
     * 添加文章
     * @param article
     */
    public void add(Article article){
        articleSearchDao.save(article);
    }

    public Page<Article> findByTitleLike(String keywords,int pageNum,int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNum,pageSize);
        return articleSearchDao.findByTitleOrContentLike(keywords,keywords,pageRequest);
    }
}
