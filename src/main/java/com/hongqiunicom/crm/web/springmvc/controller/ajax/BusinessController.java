package com.hongqiunicom.crm.web.springmvc.controller.ajax;

import com.alibaba.fastjson.JSON;
import com.hongqiunicom.crm.entity.Business;
import com.hongqiunicom.crm.services.BusinessService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by XieJiaXin on 16-12-24.
 */

@RestController
@RequestMapping("/Ajax/Business")
public class BusinessController {

    @Resource
    private BusinessService businessService;

    @RequestMapping(value = "/Show", method = RequestMethod.POST)
    @ResponseBody
    public Business show(HttpServletRequest request) {
        return businessService.get(Integer.parseInt(request.getParameter("businessId")));
    }

    @RequestMapping(value = "/List", method = RequestMethod.POST)
    @ResponseBody
    public List<Business> list(HttpServletRequest request) {
        try {
            return businessService.getBusinessPageWithOptions(10, Integer.parseInt(request.getParameter("page")), request.getParameter("state")).getList();
        } catch (
                Exception e)

        {
            e.printStackTrace();
            return null;
        }

    }

    @RequestMapping(value = "/Page", method = RequestMethod.POST)
    @ResponseBody
    public Integer page(HttpServletRequest request) {
        return businessService.getCountsWithOptions(request.getParameter("state"));
    }

    @RequestMapping(value = "/Update", method = RequestMethod.POST)
    @ResponseBody
    public Business update(@RequestBody Business business, HttpServletRequest request) {
        return businessService.manualUpdate(business);
    }

    @RequestMapping(value = "BatchUpdate", method = RequestMethod.POST)
    public Boolean batchUpdate(HttpServletRequest request) {
        return businessService.batchUpdate(JSON.parseArray(request.getParameter("businesses"), Business.class), request.getParameter("option"));
    }


}
