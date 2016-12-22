package com.hongqiunicom.crm.web.springmvc.controller.system.broadband;

import com.hongqiunicom.crm.entity.BroadbandProduct;
import com.hongqiunicom.crm.services.BroadbandProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Darkpower on 2016/12/10.
 * <p>
 * 维系挽留系统
 */

@Controller
@RequestMapping("/System/Broadband")
public class SystemBroadbandController {

    @Resource
    private BroadbandProductService broadbandProductService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "system/broadband/index";
    }

    @RequestMapping(value = "/List", method = RequestMethod.POST)
    @ResponseBody
    public List<BroadbandProduct> list(HttpServletRequest request) {
        return broadbandProductService.getBroadbandProductPage(10, Integer.parseInt(request.getParameter("nowPage"))).getList();
    }

    @RequestMapping(value = "/Page", method = RequestMethod.POST)
    @ResponseBody
    public Integer page() {
        return broadbandProductService.getAllCounts();

    }


    @RequestMapping(value = "/Update", method = RequestMethod.POST)
    @ResponseBody
    public BroadbandProduct update(@RequestBody BroadbandProduct broadbandProduct, HttpServletRequest request) {
        if(broadbandProduct.getBroadbandProductId() == 0){
            broadbandProduct.setBroadbandProductId(broadbandProductService.addBroadbandProduct(broadbandProduct));
        }else{
            broadbandProductService.update(broadbandProduct);
        }
        return broadbandProduct;
    }


}
