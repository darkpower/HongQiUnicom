package com.hongqiunicom.crm.web.springmvc.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class BaseController {
   protected static final Log log = LogFactory.getLog(BaseController.class);

   /**
    * ajax输出，返回null
    ***/
   public String ajaxJson(String content, HttpServletResponse response) {
      try {
         response.setContentType("application/json;charset=UTF-8");
         response.setHeader("Pragma", "No-cache");
         response.setHeader("Cache-Control", "no-cache");
         response.setDateHeader("Expires", 0);
         System.out.println("jsontext:" + content);
         response.getWriter().write(content);
         response.getWriter().flush();
      } catch (IOException e) {
         log.error("ajax", e);
      }
      return null;
   }
}