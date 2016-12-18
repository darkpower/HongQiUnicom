package com.hongqiunicom.crm.web.springmvc.controller.erp.card;

import com.hongqiunicom.crm.services.BroadbandService;
import com.hongqiunicom.crm.web.springmvc.view.CardTempletExcelView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Darkpower on 2016/12/10.
 *
 * 进销存系统 - 号卡
 */

@Controller
@RequestMapping("/ERP/Card")
public class CardController {

    @Resource
    private BroadbandService broadbandService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "erp/card/index";
    }


    @RequestMapping(value = "/Export", method = RequestMethod.POST)
    public ModelAndView export(ModelMap model, HttpServletRequest request) {

        CardTempletExcelView cardTempletExcelView = new CardTempletExcelView();
        return new ModelAndView(cardTempletExcelView, model);
    }

}
