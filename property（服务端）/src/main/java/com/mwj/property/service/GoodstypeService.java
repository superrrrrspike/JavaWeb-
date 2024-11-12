package com.mwj.property.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mwj.property.entity.Goodstype;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mwj.property.entity.Storage;


public interface GoodstypeService extends IService<Goodstype> {
    IPage pageCC(IPage<Goodstype> page, Wrapper wrapper);
}
