package com.hongqiunicom.crm.web.springmvc.controller.ajax;

import com.hongqiunicom.crm.entity.Broadband;
import com.hongqiunicom.crm.entity.Customer;
import com.hongqiunicom.crm.services.BroadbandService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by XieJiaXin on 16-12-24.
 */

@RestController
@RequestMapping("/Ajax/Broadband")
public class BroadbandController {

    @Resource
    private BroadbandService broadbandService;

    @RequestMapping(value = "/Show", method = RequestMethod.POST)
    @ResponseBody
    public Broadband show(HttpServletRequest request) {
        Broadband broadband = broadbandService.get(Integer.parseInt(request.getParameter("broadbandId")));
        if (broadband.getCustomer() == null)
            broadband.setCustomer(new Customer());
        return broadband;
    }

}
