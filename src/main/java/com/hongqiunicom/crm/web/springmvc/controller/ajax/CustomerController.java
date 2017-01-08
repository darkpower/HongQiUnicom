package com.hongqiunicom.crm.web.springmvc.controller.ajax;

import com.alibaba.fastjson.JSON;
import com.hongqiunicom.crm.entity.Customer;
import com.hongqiunicom.crm.services.CustomerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by XieJiaXin on 16-12-24.
 * <p>
 * Customer
 */

@RestController
@RequestMapping("/Ajax/Customer")
public class CustomerController {

    @Resource
    private CustomerService customerService;

    @RequestMapping(value = "/Show", method = RequestMethod.POST)
    @ResponseBody
    public Customer show(HttpServletRequest request) {
//        return customerService.get(Integer.parseInt(request.getParameter("customerId")));
        Customer customer =  customerService.get(Integer.parseInt(request.getParameter("customerId")));
        System.out.println(JSON.toJSONString(customer));
        return customer;
    }


    @RequestMapping(value = "/List", method = RequestMethod.POST)
    @ResponseBody
    public List<Customer> list(HttpServletRequest request) {
        try {
            return customerService.getCustomerPageWithOptions(10, Integer.parseInt(request.getParameter("page")), request.getParameter("search")).getList();
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
        return customerService.getCountsWithOptions(request.getParameter("search"));

    }


}
