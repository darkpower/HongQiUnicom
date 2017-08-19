package com.hongqiunicom.crm.web.springmvc.controller.store.manage;

import com.hongqiunicom.crm.services.BroadbandService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Created by Darkpower on 2016/12/10.
 * <p>
 * 业务受理明细
 */

@Controller
@RequestMapping("/Store/Manage")
public class StoreManageController {

    @Resource
    private BroadbandService broadbandService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "store/manage/index";
    }

}
