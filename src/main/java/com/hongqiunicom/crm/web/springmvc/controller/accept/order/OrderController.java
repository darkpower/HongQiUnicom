package com.hongqiunicom.crm.web.springmvc.controller.accept.order;

import com.hongqiunicom.crm.services.BusinessService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;

/**
 * Created by Darkpower on 2016/12/10.
 * <p>
 * 日受理清单
 */

@Controller
@RequestMapping("/Accept/UnicomOrder")
public class OrderController {

    @Resource
    private BusinessService businessService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "accept/order/index";
    }


}
