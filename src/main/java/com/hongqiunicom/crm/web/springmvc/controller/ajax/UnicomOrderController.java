package com.hongqiunicom.crm.web.springmvc.controller.ajax;

import com.hongqiunicom.crm.entity.UnicomOrder;
import com.hongqiunicom.crm.services.BusinessService;
import com.hongqiunicom.crm.services.UnicomOrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by XieJiaXin on 16-12-24.
 */

@RestController
@RequestMapping("/Ajax/UnicomOrder")
public class UnicomOrderController {

    @Resource
    private UnicomOrderService unicomOrderService;

    @RequestMapping(value = "/Create", method = RequestMethod.POST)
    @ResponseBody
    public UnicomOrder create(@RequestBody UnicomOrder unicomOrder, HttpServletRequest request) {

        return unicomOrderService.createUnicomOrder(unicomOrder);
    }

    @RequestMapping(value = "/Update", method = RequestMethod.POST)
    @ResponseBody
    public UnicomOrder update(HttpServletRequest request){
        return unicomOrderService.updateUnicomOrder(Integer.parseInt(request.getParameter("unicomOrderId")), Integer.parseInt(request.getParameter("businessId")));
    }

    @RequestMapping(value = "/List", method = RequestMethod.POST)
    @ResponseBody
    public List<UnicomOrder> list(HttpServletRequest request) {
        try{String list = request.getParameter("list");
            String startDay = request.getParameter("startDay");
            String endDay = request.getParameter("endDay");
            return unicomOrderService.getUnicomOrderPageWithOptions(10, Integer.parseInt(request.getParameter("page")), list, startDay, endDay).getList();}catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/Page", method = RequestMethod.POST)
    @ResponseBody
    public Integer page(HttpServletRequest request) {
        String list = request.getParameter("list");
        String startDay = request.getParameter("startDay");
        String endDay = request.getParameter("endDay");
        return unicomOrderService.getCountsWithOptions(list, startDay, endDay);

    }


}
