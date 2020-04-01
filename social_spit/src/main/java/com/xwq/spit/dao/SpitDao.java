package com.xwq.spit.dao;

import com.xwq.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author: Xiang Wenqin
 * @DateTime: 2020/3/26 15:45
 */
public interface SpitDao extends MongoRepository<Spit,String> {
    /**
     * 根据上级id分页查询
     * @param parentId
     * @param pageable
     * @return
     */
    Page<Spit> findByParentid(String parentId, Pageable pageable);
}
