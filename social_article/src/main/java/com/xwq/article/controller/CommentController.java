package com.xwq.article.controller;

import com.xwq.article.pojo.Comment;
import com.xwq.article.service.CommentService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Xiang Wenqin
 * @DateTime: 2020/3/27 11:59
 */
@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @RequestMapping(method= RequestMethod.POST)
    public Result save(@RequestBody Comment comment){
        commentService.add(comment);
        return new Result(true, StatusCode.OK, "提交成功 ");
    }

    @RequestMapping(value="/article/{articleId}",method= RequestMethod.GET)
    public Result findByArticleid(@PathVariable String articleId){
        return new Result(true, StatusCode.OK, "查询成功", commentService.findByArticleid(articleId));
    }
}
