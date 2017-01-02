package com.hongqiunicom.crm.web.springmvc.controller.ajax;

import com.hongqiunicom.crm.entity.Staff;
import com.hongqiunicom.crm.services.StaffService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by XieJiaXin on 16-12-24.
 */

@RestController
@RequestMapping("/Ajax/Staff")
public class StaffController {

    @Resource
    private StaffService staffService;

    @RequestMapping(value = "/SelectList", method = RequestMethod.POST)
    @ResponseBody
    public List<Staff> list(HttpServletRequest request) {
        return staffService.getSelectList();
    }


}
