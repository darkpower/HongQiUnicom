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
        UnicomOrder unicomOrder = new UnicomOrder();
        UnicomOrder pUnicomOrder = unicomOrderService.get(Integer.parseInt(request.getParameter("unicomOrderId")));
        UnicomOrderType unicomOrderType = new UnicomOrderType();
        Staff staff = new Staff();
        unicomOrder.setUnicomOrderId(pUnicomOrder.getUnicomOrderId());
        unicomOrder.setUnicomOrderDate(pUnicomOrder.getUnicomOrderDate());
        unicomOrderType.setUnicomOrderTypeId(pUnicomOrder.getUnicomOrderType().getUnicomOrderTypeId());
        unicomOrderType.setUnicomOrderTypeName(pUnicomOrder.getUnicomOrderType().getUnicomOrderTypeName());
        staff.setStaffId(pUnicomOrder.getStaff().getStaffId());
        staff.setStaffName(pUnicomOrder.getStaff().getStaffName());
        unicomOrder.setUnicomOrderType(unicomOrderType);
        unicomOrder.setStaff(staff);
        unicomOrder.setBusinesses(pUnicomOrder.getBusinesses());
        return unicomOrder;
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
        System.out.println("test" + unicomOrder.getUnicomOrderId());
        return unicomOrderService.updateUnicomOrder(unicomOrder);

    }


}
