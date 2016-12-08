package com.hongqiunicom.crm.web.springmvc.controller;

import com.hongqiunicom.crm.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class MainController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {

        return "index";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(HttpServletRequest request) {
        request.setAttribute("page", userService.getUserPage(10, 1));
        return "list";
    }
}
