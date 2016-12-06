package com.hongqiunicom.crm.web.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Darkpower on 2016/11/17.
 */

@Controller
@RequestMapping("/Task")
public class WoTaskController {


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "task/index";
    }


}
