package com.hongqiunicom.crm.web.springmvc.controller.business.day;

import com.hongqiunicom.crm.services.BroadbandService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Created by Darkpower on 2016/12/10.
 *
 * 日受理清单
 */

@Controller
@RequestMapping("/Business/Day")
public class DayController {

    @Resource
    private BroadbandService broadbandService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "business/day/index";
    }

}
