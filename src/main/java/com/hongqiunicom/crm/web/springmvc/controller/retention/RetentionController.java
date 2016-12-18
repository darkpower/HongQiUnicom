package com.hongqiunicom.crm.web.springmvc.controller.retention;

import com.hongqiunicom.crm.services.BroadbandService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Darkpower on 2016/12/10.
 *
 * 维系挽留系统
 */

@Controller
@RequestMapping("/Retention")
public class RetentionController {


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "retention/index";
    }

}
