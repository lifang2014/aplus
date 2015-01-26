package com.aplus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by lifang on 2015/1/25.
 */
@Controller
public class CommonController {

    @RequestMapping(value = "/admin/common/main")
    public String main(){
        return "/admin/main";
    }

}
