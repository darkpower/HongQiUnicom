package com.hongqiunicom.crm.web.springmvc.controller.task.xufei;

import com.hongqiunicom.crm.common.Util;
import com.hongqiunicom.crm.entity.Broadband;
import com.hongqiunicom.crm.entity.Customer;
import com.hongqiunicom.crm.services.BroadbandService;
import com.hongqiunicom.crm.web.springmvc.view.BroadbandExcelView;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Darkpower on 2016/11/17.
 * <p>
 * 沃店考核 - 宽带续费率
 */

@Controller
@RequestMapping("/Task/XuFei")
public class XuFeiController {

    @Resource
    private BroadbandService broadbandService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest request) {
        Integer allCount = broadbandService.getAllCount();
        request.setAttribute("pageCount", allCount / 10 + 1);
        return "task/xufei/index";
    }

    @RequestMapping(value = "/Page", method = RequestMethod.POST)
    @ResponseBody
    public Integer page(HttpServletRequest request) {
        String list = request.getParameter("list");
        String xuFeiType = request.getParameter("xuFeiType");
        String systemType = request.getParameter("systemType");
        return broadbandService.getCountsWithOptions(list, xuFeiType, systemType);

    }

    @RequestMapping(value = "/List", method = RequestMethod.POST)
    @ResponseBody
    public List<Broadband> list(HttpServletRequest request) {
        String list = request.getParameter("list");
        String xuFeiType = request.getParameter("xuFeiType");
        String systemType = request.getParameter("systemType");
        return broadbandService.getBroadbandPageWithOption(10, Integer.parseInt(request.getParameter("page")), list, xuFeiType, systemType).getList();
    }

    @RequestMapping(value = "/Show", method = RequestMethod.POST)
    @ResponseBody
    public Broadband show(HttpServletRequest request) {
        Broadband broadband = broadbandService.get(Integer.parseInt(request.getParameter("broadbandId")));
        if (broadband.getCustomer() == null)
            broadband.setCustomer(new Customer());
        return broadband;
    }

    @RequestMapping(value = "/Upload", method = RequestMethod.POST)
    @ResponseBody
    public boolean upload(@RequestParam MultipartFile excelFile, HttpServletRequest request) throws IOException {

        //如果只是上传一个文件，则只需要MultipartFile类型接收文件即可，而且无需显式指定@RequestParam注解
        //如果想上传多个文件，那么这里就要用MultipartFile[]类型来接收文件，并且还要指定@RequestParam注解
        //并且上传多个文件时，前台表单中的所有<input type="file"/>的name都应该是excelFiles，否则参数里的excelFiles无法获取到所有上传的文件

        try {
            File newFile = null;

            if (excelFile.isEmpty()) {
                System.out.println("文件未上传");
            } else {
                System.out.print("文件长度: " + excelFile.getSize() + "  ");
                System.out.print("文件类型: " + excelFile.getContentType() + "  ");
                System.out.print("文件名称: " + excelFile.getName() + "  ");
                System.out.print("文件原名: " + excelFile.getOriginalFilename() + "  ");
                String newFileName = new SimpleDateFormat("yyMMddHHmmss").format(new Date()) + "." + Util.getPostfix(excelFile.getOriginalFilename());
                System.out.println("新生成文件名为: " + newFileName);
                //如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中
                String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/xufei");
                //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的
                newFile = new File(realPath, newFileName);
                FileUtils.copyInputStreamToFile(excelFile.getInputStream(), newFile);
            }
            broadbandService.batchUpdateByExcel(newFile);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;


    }

    @RequestMapping(value = "/Update", method = RequestMethod.POST)
    @ResponseBody
    public Broadband update(@RequestBody Broadband broadband, HttpServletRequest request) {
        return broadbandService.manualUpdate(broadband);
    }


    @RequestMapping(value = "/Export", method = RequestMethod.POST)
    public ModelAndView export(ModelMap model, HttpServletRequest request) {
        String list = request.getParameter("list");
        String xuFeiType = request.getParameter("xuFeiType");
        String systemType = request.getParameter("systemType");
        List<Broadband> broadbandList = broadbandService.getBroadbandsWithOptions(list, xuFeiType, systemType);
        Map<String, Object> attributesMap = new HashMap<String, Object>();
        attributesMap.put("list", broadbandList);
        BroadbandExcelView broadbandExcelView = new BroadbandExcelView();
        broadbandExcelView.setAttributesMap(attributesMap);
        return new ModelAndView(broadbandExcelView, model);
    }
}
