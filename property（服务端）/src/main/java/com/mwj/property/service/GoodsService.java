package com.mwj.property.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mwj.property.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mwj.property.entity.Storage;


public interface GoodsService extends IService<Goods> {
    IPage pageCC(IPage<Goods> page, Wrapper wrapper);
}
