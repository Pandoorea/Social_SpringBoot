package com.xwq.spit.controller;

import com.xwq.spit.pojo.Spit;
import com.xwq.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Xiang Wenqin
 * @DateTime: 2020/3/26 15:57
 */
@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {
    @Autowired
    private SpitService spitService;

    @Autowired
    private RedisTemplate redisTemplate;

    /***
     * 点赞
     * @param id
     * @return */
    @RequestMapping(value="/thumbup/{id}",method=RequestMethod.PUT)
    public Result updateThumbup(@PathVariable String id){
        //判断用户是否点过赞
        String userId="123456";
        if (redisTemplate.opsForValue().get("thumbup_"+userId+"_"+id)!=null){
            return new Result(false,StatusCode.REPERROR,"你已经点过赞了");
        }
        spitService.updateThumbup(id);
        //没有点赞则存入缓存
        redisTemplate.opsForValue().set( "thumbup_"+userId+"_"+ id,"1");
        return new Result(true,StatusCode.OK,"点赞成功");
    }


    @RequestMapping(value="/comment/{parentId}/{page}/{size}",method=RequestMethod.GET)
    public Result findByParentid(@PathVariable String parentId, @PathVariable int page,@PathVariable int size) {
        Page<Spit> spitPage = spitService.findByParentid(parentId, page, size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<Spit>(spitPage.getTotalElements(),spitPage.getContent()));
    }


    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",spitService.findAll());
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Result findOne(@PathVariable String id){
        return new Result(true, StatusCode.OK,"查询成功",spitService.findById(id));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Spit spit){
        spitService.addSpit(spit);
        return new Result(true, StatusCode.OK,"添加成功");
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Result add(@RequestBody Spit spit, @PathVariable String id){
        spit.set_id(id);
        spitService.update(spit);
        return new Result(true, StatusCode.OK,"修改成功");
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String id){
        spitService.deleteById(id);
        return new Result(true, StatusCode.OK,"删除成功");
    }


}
