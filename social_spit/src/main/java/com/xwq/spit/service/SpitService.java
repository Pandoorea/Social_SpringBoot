package com.xwq.spit.service;

import com.xwq.spit.dao.SpitDao;
import com.xwq.spit.pojo.Spit;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.Date;
import java.util.List;

/**
 * @Author: Xiang Wenqin
 * @DateTime: 2020/3/26 15:47
 */
@Service
public class SpitService {
    @Autowired
    private SpitDao spitDao;
    @Autowired
    private IdWorker idWorker;

    @Autowired
    private MongoTemplate mongoTemplate;


    /**
     * 吐槽点赞
     * @param id
     */
    public void updateThumbup(String id){

        //效率低
        /*Spit spit = spitDao.findById(id).get();
        if (spit!=null)
            spit.setThumbup(spit.getThumbup()+1);
        spitDao.save(spit);*/

        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.inc("thumbup",1);
        mongoTemplate.updateFirst(query,update,"spit");
    }

    public Page<Spit> findByParentid(String parentId,int pageNum,int pageSize){
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Spit> spitPage = spitDao.findByParentid(parentId, pageable);
        return spitPage;
    }
    /**
     * 查询全部吐槽
     * @return
     */
    public List<Spit> findAll(){
        return spitDao.findAll();
    }

    /**
     * 根据主键查询实体
     * @param id
     * @return
     */
    public Spit findById(String id){
        return spitDao.findById(id).get();
    }

    /**
     * 增加,发布吐槽
     * @param spit
     */
    public void addSpit(Spit spit){
        spit.set_id(String.valueOf(idWorker.nextId()));
        spit.set_id( idWorker.nextId()+"" );
        spit.setPublishtime(new Date());//发布日期
        spit.setVisits(0);//浏览量
        spit.setShare(0);//分享数
        spit.setThumbup(0);//点赞数
        spit.setComment(0);//回复数
        spit.setState("1");//状态
        if (ObjectUtils.isNotEmpty(spit.getParentid())){
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
            Update update = new Update();
            update.inc("comment",1);
            mongoTemplate.updateFirst(query,update,"spit");
        }
        spitDao.save(spit);
    }

    /**
     * 根据id删除
     * @param id
     */
    public void deleteById(String id){
        spitDao.deleteById(id);
    }

    /**
     * 修改
     * @param spit
     */
    public void update(Spit spit){
        spitDao.save(spit);
    }
}
