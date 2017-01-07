package com.hongqiunicom.crm.web.springmvc.controller.accept.customer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Darkpower on 2016/12/10.
 * <p>
 * 日受理清单
 */

@Controller
@RequestMapping("/Accept/Customer")
public class ShowCustomerController {


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "accept/customer/index";
    }


}
