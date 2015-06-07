package com.aplus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by lifang on 2015/6/3.
 */
@Controller
@RequestMapping("/admin/hr")
public class HRController extends BaseController{

    private static final String PATH_HR_VIEW = "/admin/hr/build";

    @RequestMapping(value = "/build", method = RequestMethod.GET)
    public String view(){
        return PATH_HR_VIEW;
    }

}
