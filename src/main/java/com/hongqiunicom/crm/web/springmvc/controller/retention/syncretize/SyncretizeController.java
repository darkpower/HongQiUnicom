package com.hongqiunicom.crm.web.springmvc.controller.retention.syncretize;

import com.hongqiunicom.crm.entity.Broadband;
import com.hongqiunicom.crm.services.BroadbandService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Darkpower on 2016/12/10.
 * <p>
 * 进销存系统
 */

@Controller
@RequestMapping("/Retention/Syncretize")
public class SyncretizeController {

    @Resource
    private BroadbandService broadbandService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "retention/syncretize/index";
    }


    @RequestMapping(value = "/Page", method = RequestMethod.POST)
    @ResponseBody
    public Integer page(HttpServletRequest request) {
        return broadbandService.getCountsWithOptions("全部", "已续费", "CBSS", request.getParameter("date"));

    }

    @RequestMapping(value = "/List", method = RequestMethod.POST)
    @ResponseBody
    public List<Broadband> list(HttpServletRequest request) {
        return broadbandService.getBroadbandPageWithOption(10, Integer.parseInt(request.getParameter("page")), "全部宽带续费清单", "已续费", "CBSS", request.getParameter("date")).getList();
    }


}
