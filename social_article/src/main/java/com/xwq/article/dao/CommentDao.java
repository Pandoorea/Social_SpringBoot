package com.xwq.article.dao;

import com.xwq.article.pojo.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @Author: Xiang Wenqin
 * @DateTime: 2020/3/27 11:36
 */
public interface CommentDao extends MongoRepository<Comment,String> {
    /*** 根据文章ID查询评论列表
     *  @param articleId
     *  @return */
    List<Comment> findByArticleid(String articleId);
}
