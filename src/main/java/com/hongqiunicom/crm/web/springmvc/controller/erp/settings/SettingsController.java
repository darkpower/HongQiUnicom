package com.hongqiunicom.crm.web.springmvc.controller.erp.settings;

import com.hongqiunicom.crm.services.BroadbandService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Created by Darkpower on 2016/12/10.
 *
 * 进销存系统
 */

@Controller
@RequestMapping("/ERP/Settings")
public class SettingsController {

    @Resource
    private BroadbandService broadbandService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "erp/settings/index";
    }

}
