package com.hongqiunicom.crm.web.springmvc.controller.accept.businesses;

import com.hongqiunicom.crm.common.Util;
import com.hongqiunicom.crm.services.BusinessService;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Darkpower on 2016/12/10.
 *
 * 日受理清单
 */

@Controller
@RequestMapping("/Accept/Businesses")
public class BusinessesController {

    @Resource
    private BusinessService businessService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "accept/businesses/index";
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
                String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/accept");
                //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的
                newFile = new File(realPath, newFileName);
                FileUtils.copyInputStreamToFile(excelFile.getInputStream(), newFile);
            }
            businessService.batchUpdateByExcel(newFile);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;


    }


}
