package com.xwq.base.controller;

import com.xwq.base.pojo.Label;
import com.xwq.base.service.LabelService;
import entity.PageResult;
import entity.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.List;

/**
 * @Author: Xiang Wenqin
 * @DateTime: 2020/3/17 11:19
 */
@RestController
@CrossOrigin
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    @RequestMapping(method = RequestMethod.GET)
    public ResultJson findAll(){
        return ResultJson.success(labelService.findAll());
    }

    @RequestMapping(value = "/{labelId}",method = RequestMethod.GET)
    public ResultJson findById(@PathVariable String labelId){
        return ResultJson.success(labelService.findById(labelId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResultJson save(@RequestBody Label label){
        labelService.save(label);
        return ResultJson.success("保存成功");
    }
    @RequestMapping(value = "/{labelId}",method = RequestMethod.PUT)
    public ResultJson update(@RequestBody Label label, @PathVariable String labelId){
        label.setId(labelId);
        labelService.update(label);
        return ResultJson.success("更新成功");
    }
    @RequestMapping(value = "/{labelId}",method = RequestMethod.POST)
    public ResultJson deleteById(@PathVariable String labelId){
        labelService.deleteById(labelId);
        return ResultJson.success("删除成功");
    }

    @RequestMapping(value = "/query",method = RequestMethod.POST)
    public ResultJson queryLabel(@RequestBody Label label){
        List<Label> list = labelService.queryLabel(label);
        return ResultJson.success(list);
    }
    @RequestMapping(value = "/pageQuery/{pageNum}/{pageSize}",method = RequestMethod.POST)
    public ResultJson queryPage(@RequestBody Label label,@PathVariable int pageNum, @PathVariable int pageSize){
        Page<Label> page = labelService.queryPage(label,pageNum,pageSize);
        return ResultJson.success(new PageResult<Label>(page.getTotalElements(),page.getContent()));
    }
}
