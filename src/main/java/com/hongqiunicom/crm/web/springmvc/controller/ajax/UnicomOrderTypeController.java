package com.hongqiunicom.crm.web.springmvc.controller.ajax;

import com.hongqiunicom.crm.entity.UnicomOrderType;
import com.hongqiunicom.crm.services.UnicomOrderTypeService;
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
@RequestMapping("/Ajax/UnicomOrderType")
public class UnicomOrderTypeController {

    @Resource
    private UnicomOrderTypeService unicomOrderTypeService;

    @RequestMapping(value = "/SelectList", method = RequestMethod.POST)
    @ResponseBody
    public List<UnicomOrderType> list(HttpServletRequest request) {
        List<UnicomOrderType> list =  unicomOrderTypeService.getSelectList();
        return list;
    }


}
