package com.hongqiunicom.crm.web.springmvc.controller.accept.verify;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Darkpower on 2016/12/10.
 * <p>
 * 日受理清单
 */

@Controller
@RequestMapping("/Accept/Verify")
public class VerifyController {


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "accept/verify/index";
    }


}
