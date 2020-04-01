package com.xwq.article.service;

import com.xwq.article.dao.CommentDao;
import com.xwq.article.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.List;

/**
 * @Author: Xiang Wenqin
 * @DateTime: 2020/3/27 11:37
 */
@Service
public class CommentService {
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private IdWorker idWorker;

    public List<Comment> findByArticleid(String articleId){
        return commentDao.findByArticleid(articleId);
    }

    public void add(Comment comment){
        comment.set_id(String.valueOf(idWorker.nextId()));
        commentDao.save(comment);
    }

    public void deleteById(String id){
        commentDao.deleteById(id);
    }

    public void update(Comment comment){
        commentDao.save(comment);
    }
    public List<Comment> findAll(){
        return commentDao.findAll();
    }
    public Comment findById(String id){
        return commentDao.findById(id).get();
    }
}
