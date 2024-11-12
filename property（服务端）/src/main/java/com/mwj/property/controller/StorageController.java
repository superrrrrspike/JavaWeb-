package com.mwj.property.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mwj.property.common.QueryPageParam;
import com.mwj.property.common.Result;
import com.mwj.property.entity.Menu;
import com.mwj.property.entity.Storage;
import com.mwj.property.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

//类似于GoodsController，处理仓库Storage的CRUD操作和分页查询。
//新增了一个@GetMapping("/list")方法，用于获取所有仓库的列表。
@RestController
@RequestMapping("/storage")
public class StorageController {
    @Autowired
    private StorageService storageService;
    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Storage storage) {
        return storageService.save(storage)?Result.suc():Result.fail();
    }
    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Storage storage) {
        return storageService.updateById(storage)?Result.suc():Result.fail();
    }
    //删除
    @GetMapping("/del")
    public Result del(@RequestParam String id) {
        return storageService.removeById(id)?Result.suc():Result.fail();
    }
    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query) {
        HashMap param = query.getParam();
        String name = (String)param.get("name");
        Page<Storage> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        LambdaQueryWrapper<Storage> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(name) && !"null".equals(name)){
            lambdaQueryWrapper.like(Storage::getName,name);
        }
        IPage result = storageService.pageCC(page,lambdaQueryWrapper);
        return Result.suc(result.getRecords(), result.getTotal());
    }
    @GetMapping("/list")
    public Result list(){
        List list = storageService.list();
        return Result.suc(list);
        //类似于GoodsController，处理仓库Storage的CRUD操作和分页查询。
        //新增了一个@GetMapping("/list")方法，用于获取所有仓库的列表。
    }
}
