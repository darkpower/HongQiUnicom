package com.hongqiunicom.crm.web.springmvc.controller.erp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Darkpower on 2016/12/10.
 *
 * 进销存系统
 */

@Controller
@RequestMapping("/ERP")
public class ERPController {


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "erp/index";
    }

}
