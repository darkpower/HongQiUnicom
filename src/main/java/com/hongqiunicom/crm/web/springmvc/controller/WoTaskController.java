package com.hongqiunicom.crm.web.springmvc.controller;

import com.hongqiunicom.crm.services.BroadbandService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Darkpower on 2016/11/17.
 */

@Controller
@RequestMapping("/Task")
public class WoTaskController {

    @Resource
    BroadbandService broadbandService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "task/index";
    }

    @RequestMapping(value = "/Counts", method = RequestMethod.POST)
    @ResponseBody
    public Integer[] taskCounts(HttpServletRequest request) {
        return new Integer[]{
                broadbandService.getCountsWithOptions("次月宽带续费清单", "全部", "全部"),
                broadbandService.getCountsWithOptions("次月宽带续费清单", "已续费", "全部")
        };
    }

}
