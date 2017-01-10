package com.hongqiunicom.crm.web.springmvc.controller.accept.save;

import com.hongqiunicom.crm.common.Util;
import com.hongqiunicom.crm.services.BroadbandService;
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
 * <p>
 * 业务受理明细
 */

@Controller
@RequestMapping("/Accept/Save")
public class SaveController {

    @Resource
    private BroadbandService broadbandService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "accept/save/index";
    }

    @RequestMapping(value = "/Upload", method = RequestMethod.POST)
    @ResponseBody
    public boolean upload(@RequestParam MultipartFile picFile, HttpServletRequest request) throws IOException {

        //如果只是上传一个文件，则只需要MultipartFile类型接收文件即可，而且无需显式指定@RequestParam注解
        //如果想上传多个文件，那么这里就要用MultipartFile[]类型来接收文件，并且还要指定@RequestParam注解
        //并且上传多个文件时，前台表单中的所有<input type="file"/>的name都应该是excelFiles，否则参数里的excelFiles无法获取到所有上传的文件
        System.out.println(request.getParameter("unicomOrderId"));
        try {
            File newFile = null;

            if (picFile.isEmpty()) {
                System.out.println("文件未上传");
            } else {
                System.out.print("文件 长度: " + picFile.getSize() + "  ");
                System.out.print("文件 类型: " + picFile.getContentType() + "  ");
                System.out.print("文件 名称: " + picFile.getName() + "  ");
                System.out.print("文件 原名: " + picFile.getOriginalFilename() + "  ");
                String newFileName = request.getParameter("unicomOrderId") + "." + Util.getPostfix(picFile.getOriginalFilename());
                System.out.println("新生成文件名为: " + newFileName);
                //如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中
//                String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload/accept");
                String realPath = "D:\\HongqiUnicom\\Accept\\SaveData";
                //这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的
                newFile = new File(realPath, newFileName);
                FileUtils.copyInputStreamToFile(picFile.getInputStream(), newFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;


    }


}
