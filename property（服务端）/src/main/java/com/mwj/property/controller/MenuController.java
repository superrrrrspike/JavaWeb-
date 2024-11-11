package com.mwj.property.controller;


import com.mwj.property.common.Result;
import com.mwj.property.entity.Menu;
import com.mwj.property.entity.User;
import com.mwj.property.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;
    @GetMapping("/list")
    public Result list(@RequestParam String roleId){
        List list = menuService.lambdaQuery().like(Menu::getMenuright,roleId).list();
        return Result.suc(list);
        //@GetMapping("/list")：处理GET请求，根据角色ID获取菜单列表。
        //使用MyBatis-Plus的lambdaQuery方法，根据menuright字段进行模糊查询。
    }
}
