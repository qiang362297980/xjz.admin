package com.secondBack.controller;

import com.secondBack.entity.XcxPayLog;
import com.secondBack.service.XcxPayLogService;
import com.tool.basic.StringUtils;
import com.tool.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/13.
 */
@Controller
@RequestMapping(value = "/payLog")
public class XcxPayLogController {

    @Autowired
    private XcxPayLogService xcxPayLogService;

    @ModelAttribute
    public XcxPayLog get(@RequestParam(required = false) String id) {
        XcxPayLog entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = xcxPayLogService.get(id);
        }
        if (entity == null) {
            entity = new XcxPayLog();
        }
        return entity;
    }

    @RequestMapping(value = "/list")
    public String list(Model model, XcxPayLog xcxPayLog,
                       HttpServletRequest request, HttpServletResponse response){
        Page<XcxPayLog> page =
                xcxPayLogService.list(new Page<XcxPayLog>(request,response),xcxPayLog);
        model.addAttribute("page",page);
        return "/xcxPayLog/xcxPayLogList";
    }

    @RequestMapping(value = "/view")
    public String view(Model model, XcxPayLog xcxPayLog){
        XcxPayLog payLog = xcxPayLogService.addInfo(xcxPayLog);
        model.addAttribute("xcxPayLog",payLog);
        return "/xcxPayLog/xcxPayLogView";
    }

    @RequestMapping(value = "/showImg")
    public void showImg(XcxPayLog xcxPayLog, HttpServletRequest request, HttpServletResponse response){
        InputStream inputStream = null;
        OutputStream writer = null;
        try {
            inputStream = new FileInputStream(xcxPayLogService.addInfo(xcxPayLog).getGoodsUrl());
            writer = response.getOutputStream();

            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(bytes) ) != -1) {
                writer.write(bytes,0,len);
            }
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(inputStream != null){
                    inputStream.close();
                }
                if(writer != null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
