package com.aplus.controller;

import com.aplus.entity.MenuEntity;
import com.aplus.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * <pre>
 *     <li>1. /admin/common/main.html 后台管理页面</li>
 *     <li>2. /admin/common/404.html 404错误页面</li>
 * </pre>
 * Created by lifang on 2015/1/25.
 */
@Controller
public class CommonController extends BaseController{


    @Autowired
    private MenuService menuService;

    private static final String VIEW_MAIN = "/admin/common/main";
    private static final String VIEW_404 = "/admin/404/404";

    @RequestMapping(value = "/admin/common/main", method = RequestMethod.GET)
    public String main(ModelMap model){
        List<MenuEntity> menuEntityList = menuService.findAll();
        model.put("menus", menuEntityList);
        return VIEW_MAIN;
    }

    @RequestMapping(value = "/admin/common/404")
    public String error404(){
        return VIEW_404;
    }

}
