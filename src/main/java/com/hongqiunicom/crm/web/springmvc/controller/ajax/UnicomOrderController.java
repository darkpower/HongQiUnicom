package com.hongqiunicom.crm.web.springmvc.controller.ajax;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hongqiunicom.crm.entity.UnicomOrder;
import com.hongqiunicom.crm.services.UnicomOrderService;
import com.hongqiunicom.crm.web.springmvc.controller.BaseController;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Modify by XieJiaXin on 2017-1-3
 * <p>
 * UnicomOrderController
 */

@RestController
@RequestMapping("/Ajax/UnicomOrder")
public class UnicomOrderController extends BaseController {

    @Resource
    private UnicomOrderService unicomOrderService;

    @RequestMapping(value = "/Show", method = RequestMethod.POST)
    @ResponseBody
    public void show(HttpServletRequest request, HttpServletResponse response) {
//        return unicomOrderService.get(Integer.parseInt(request.getParameter("unicomOrderId")));
        ajaxJson(JSON.toJSONString(unicomOrderService.get(Integer.parseInt(request.getParameter("unicomOrderId"))), SerializerFeature.DisableCircularReferenceDetect), response);
    }

    @RequestMapping(value = "/Create", method = RequestMethod.POST)
    @ResponseBody
    public UnicomOrder create(@RequestBody UnicomOrder unicomOrder) {
        return unicomOrderService.createUnicomOrder(unicomOrder);
    }

    @RequestMapping(value = "/Join", method = RequestMethod.POST)
    @ResponseBody
    public UnicomOrder join(@RequestBody UnicomOrder unicomOrder) {

            return unicomOrderService.updateUnicomOrderJoinBusiness(unicomOrder);

    }

    @RequestMapping(value = "/List", method = RequestMethod.POST)
    public void list(HttpServletRequest request, HttpServletResponse response) {
        String state = request.getParameter("state");
        String verify = request.getParameter("verify");
        ajaxJson(JSON.toJSONString(unicomOrderService.getUnicomOrderPageWithOptions(10, Integer.parseInt(request.getParameter("page")), state, verify).getList(), SerializerFeature.DisableCircularReferenceDetect), response);
//        return unicomOrderService.getUnicomOrderPageWithOptions(10, Integer.parseInt(request.getParameter("page")), list, startDay, endDay).getList();
    }

    @RequestMapping(value = "/Page", method = RequestMethod.POST)
    @ResponseBody
    public Integer page(HttpServletRequest request) {
        String state = request.getParameter("state");
        String verify = request.getParameter("verify");
        return unicomOrderService.getCountsWithOptions(state, verify);

    }

    @RequestMapping(value = "/Update", method = RequestMethod.POST)
    @ResponseBody
    public UnicomOrder update(@RequestBody UnicomOrder unicomOrder) {
        return unicomOrderService.updateUnicomOrder(unicomOrder);

    }


}
