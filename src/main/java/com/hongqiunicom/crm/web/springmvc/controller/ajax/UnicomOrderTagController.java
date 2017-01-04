package com.hongqiunicom.crm.web.springmvc.controller.ajax;

import com.hongqiunicom.crm.entity.UnicomOrderTag;
import com.hongqiunicom.crm.services.UnicomOrderTagService;
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
@RequestMapping("/Ajax/UnicomOrderTag")
public class UnicomOrderTagController {

    @Resource
    private UnicomOrderTagService unicomOrderTagService;

    @RequestMapping(value = "/SelectList", method = RequestMethod.POST)
    @ResponseBody
    public List<UnicomOrderTag> list(HttpServletRequest request) {
        List<UnicomOrderTag> list = unicomOrderTagService.getSelectList();
        return list;
    }


}
