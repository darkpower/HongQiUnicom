package com.hongqiunicom.crm.web.springmvc.controller.ajax;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hongqiunicom.crm.entity.Staff;
import com.hongqiunicom.crm.entity.UnicomOrder;
import com.hongqiunicom.crm.entity.UnicomOrderType;
import com.hongqiunicom.crm.services.UnicomOrderService;
import com.hongqiunicom.crm.web.springmvc.controller.BaseController;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by XieJiaXin on 16-12-24.
 */

@RestController
@RequestMapping("/Ajax/UnicomOrder")
public class UnicomOrderController extends BaseController {

    @Resource
    private UnicomOrderService unicomOrderService;

    @RequestMapping(value = "/Show", method = RequestMethod.POST)
    @ResponseBody
    public UnicomOrder show(HttpServletRequest request) {

        return unicomOrderService.get(Integer.parseInt(request.getParameter("unicomOrderId")));
    }

    @RequestMapping(value = "/Create", method = RequestMethod.POST)
    @ResponseBody
    public UnicomOrder create(@RequestBody UnicomOrder unicomOrder, HttpServletRequest request) {
        return unicomOrderService.createUnicomOrder(unicomOrder);
    }

    @RequestMapping(value = "/Join", method = RequestMethod.POST)
    @ResponseBody
    public UnicomOrder update(HttpServletRequest request) {
        return unicomOrderService.updateUnicomOrderJoinBusiness(Integer.parseInt(request.getParameter("unicomOrderId")), Integer.parseInt(request.getParameter("businessId")));
    }

    @RequestMapping(value = "/List", method = RequestMethod.POST)
    public void list(HttpServletRequest request, HttpServletResponse response) {
        String list = request.getParameter("list");
        String startDay = request.getParameter("startDay");
        String endDay = request.getParameter("endDay");
        ajaxJson(JSON.toJSONString(unicomOrderService.getUnicomOrderPageWithOptions(10, Integer.parseInt(request.getParameter("page")), list, startDay, endDay).getList(), SerializerFeature.DisableCircularReferenceDetect), response);
//        return unicomOrderService.getUnicomOrderPageWithOptions(10, Integer.parseInt(request.getParameter("page")), list, startDay, endDay).getList();
    }

    @RequestMapping(value = "/Page", method = RequestMethod.POST)
    @ResponseBody
    public Integer page(HttpServletRequest request) {
        String list = request.getParameter("list");
        String startDay = request.getParameter("startDay");
        String endDay = request.getParameter("endDay");
        return unicomOrderService.getCountsWithOptions(list, startDay, endDay);

    }

    @RequestMapping(value = "/Update", method = RequestMethod.POST)
    @ResponseBody
    public UnicomOrder update(@RequestBody UnicomOrder unicomOrder, HttpServletRequest request) {
        return unicomOrderService.updateUnicomOrder(unicomOrder);

    }


}
