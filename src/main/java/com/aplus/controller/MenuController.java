package com.aplus.controller;

import com.aplus.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by lifang on 2015/5/8.
 */
@RequestMapping("/admin/menu")
public class MenuController extends BaseController{

    private static final String M_PATH_MENU_BUILD = "/admin/menu/build";// 功能菜单列表
    private static final String M_PATH_MENU_EDIT = "/admin/menu/edit";// 编辑页面

    @Autowired
    private MenuService menuService;

    /**
     * 菜单列表
     * @return
     */
    @RequestMapping(value = "/build", method = RequestMethod.GET)
    public String list(ModelMap model) {
        model.put("menus", menuService.findAll());
        return M_PATH_MENU_BUILD;
    }


    @RequestMapping(value = "/save", method=RequestMethod.POST)
    @ResponseBody
    public String save(BindingResult binding,HttpServletResponse response){
        return "";
    }


    /**
     * 菜单编辑
     * @return
     */
    @RequestMapping(value="/edit",method=RequestMethod.GET)
    public String edit(Long menuId,ModelMap model){
        model.put("menu", menuService.findById(menuId));
        return M_PATH_MENU_EDIT;
    }

}
