package com.mwj.property.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mwj.property.entity.Storage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mwj.property.entity.User;

public interface StorageService extends IService<Storage> {
    IPage pageCC(IPage<Storage> page, Wrapper wrapper);
}
