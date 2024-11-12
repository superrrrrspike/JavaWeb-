package com.mwj.property.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mwj.property.entity.Goods;
import com.mwj.property.entity.Record;
import com.baomidou.mybatisplus.extension.service.IService;

public interface RecordService extends IService<Record> {
    IPage pageCC(IPage<Record> page, Wrapper wrapper);
}
