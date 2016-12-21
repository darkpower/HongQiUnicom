package com.hongqiunicom.crm.web.springmvc.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Darkpower on 2016/12/10.
 *
 * 维系挽留系统
 */

@Controller
@RequestMapping("/System")
public class SystemController {


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "system/index";
    }

}
