package com.hongqiunicom.crm.web.springmvc.controller.accept;

import com.hongqiunicom.crm.services.BroadbandService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Created by Darkpower on 2016/12/10.
 *
 * 业务受理明细
 */

@Controller
@RequestMapping("/Accept")
public class AcceptController {

    @Resource
    private BroadbandService broadbandService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "accept/index";
    }

}
