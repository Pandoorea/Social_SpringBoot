package com.xwq.qa.dao;

import com.xwq.qa.pojo.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{

    @Query(value = "SELECT * FROM tb_problem p, tb_pl pl WHERE p.id=pl.problemid AND labelid=? ORDER BY createtime DESC",nativeQuery = true)
    Page<Problem> newlist(String labelid, Pageable pageable);

    @Query(value = "SELECT * FROM tb_problem p, tb_pl pl WHERE p.id=pl.problemid AND labelid=? ORDER BY reply DESC",nativeQuery = true)
    Page<Problem> hotlist(String labelid, Pageable pageable);

    @Query(value = "SELECT * FROM tb_problem p, tb_pl pl WHERE p.id=pl.problemid AND labelid=? AND reply =0 ORDER BY createtime DESC",nativeQuery = true)
    Page<Problem> waitlist(String labelid, Pageable pageable);
}
