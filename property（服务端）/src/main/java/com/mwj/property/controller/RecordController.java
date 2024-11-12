package com.mwj.property.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mwj.property.common.QueryPageParam;
import com.mwj.property.common.Result;
import com.mwj.property.entity.Goods;
import com.mwj.property.entity.Record;
import com.mwj.property.service.GoodsService;
import com.mwj.property.service.RecordService;
import java.time.LocalDateTime;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/record")
public class RecordController {
  @Autowired private RecordService recordService;
  @Autowired private GoodsService goodsService;

  @PostMapping("/save")
  public Result save(@RequestBody Record record) {

    int n = record.getCount();
    // 出库
    if ("2".equals(record.getAction())) {
      n = -n;
      record.setCount(n);
    }
    System.out.println("record"+record);
    record.setCreatetime(LocalDateTime.now());

    return recordService.save(record) ? Result.suc() : Result.fail();
    //    }
  }

  // 审核
  @PostMapping("/mod")
  public Result mod(@RequestBody Record record) {
    System.out.println(record);
    Goods goods = goodsService.getById(record.getGoods());

    System.out.println(goods.getCount() + record.getCount());
    if ((goods.getCount() + record.getCount()) < 0)
      return Result.fail("仓库物品剩余" + goods.getCount() + "件");
    else {
      int num = goods.getCount() + record.getCount();
      goods.setCount(num);
      record.setStatus("Y");
      record.setRecordtime(LocalDateTime.now());
      record.setAdminId(record.getAdminId());
      goodsService.updateById(goods);
      return recordService.updateById(record) ? Result.suc() : Result.fail();
    }
  }
  @PostMapping("/refuse")
  public Result refuse(@RequestBody Record record) {
    System.out.println(record);

      record.setStatus("S");
      record.setRecordtime(LocalDateTime.now());
      record.setAdminId(record.getAdminId());
      return recordService.updateById(record) ? Result.suc() : Result.fail();

  }
  @PostMapping("/listPage")
  public Result listPage(@RequestBody QueryPageParam query) {
    HashMap param = query.getParam();
    String name = (String) param.get("name");
    String storage = (String) param.get("storage");
    String goodstype = (String) param.get("goodstype");
    String status = (String) param.get("status");

    String adminId = (String) param.get("adminId");
    String userid = (String) param.get("userid");
    Page<Record> page = new Page();
    page.setCurrent(query.getPageNum());
    page.setSize(query.getPageSize());

    QueryWrapper<Record> queryWrapper = new QueryWrapper();
    queryWrapper.apply(" a.goods=b.id and b.storage=c.id and b.goodsType=d.id ");
    if (StringUtils.isNotBlank(name) && !"null".equals(name)) {
      queryWrapper.like("b.name", name);
    }
    if (StringUtils.isNotBlank(storage) && !"null".equals(storage)) {
      queryWrapper.eq("c.id", storage);
    }
    if (StringUtils.isNotBlank(goodstype) && !"null".equals(goodstype)) {
      queryWrapper.eq("d.id", goodstype);
    }
    if (StringUtils.isNotBlank(userid) && !"null".equals(userid)) {
      queryWrapper.like("a.userId", userid);
      System.out.println(userid);
    }
    if (StringUtils.isNotBlank(adminId) && !"null".equals(adminId)) {
      queryWrapper.like("a.admin_id", adminId);
      System.out.println(adminId);
    }
    if (StringUtils.isNotBlank(status) && !"null".equals(status)) {
      queryWrapper.like("a.status", status);
    }
    //    System.out.println(queryWrapper.);
    IPage result = recordService.pageCC(page, queryWrapper);
    return Result.suc(result.getRecords(), result.getTotal());
  }
}
