package com.hongqiunicom.crm.web.springmvc.controller.ajax;

import com.hongqiunicom.crm.entity.Broadband;
import com.hongqiunicom.crm.entity.Customer;
import com.hongqiunicom.crm.services.BroadbandService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @RequestMapping(value = "/List", method = RequestMethod.POST)
    @ResponseBody
    public List<Broadband> list(HttpServletRequest request) {
        String list = request.getParameter("list");
        String xuFeiType = request.getParameter("xuFeiType");
        String systemType = request.getParameter("systemType");
        return broadbandService.getBroadbandPageWithOptions(10, Integer.parseInt(request.getParameter("page")), list, xuFeiType, systemType).getList();
    }

    @RequestMapping(value = "/Page", method = RequestMethod.POST)
    @ResponseBody
    public Integer page(HttpServletRequest request) {
        String list = request.getParameter("list");
        String xuFeiType = request.getParameter("xuFeiType");
        String systemType = request.getParameter("systemType");
        return broadbandService.getCountsWithOptions(list, xuFeiType, systemType);

    }

    @RequestMapping(value = "/Update", method = RequestMethod.POST)
    @ResponseBody
    public Broadband update(@RequestBody Broadband broadband, HttpServletRequest request) {
        return broadbandService.manualUpdate(broadband);
    }





}
