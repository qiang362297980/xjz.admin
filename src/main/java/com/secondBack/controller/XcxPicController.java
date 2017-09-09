package com.secondBack.controller;

import com.secondBack.entity.XcxPic;
import com.secondBack.entity.XcxStore;
import com.secondBack.service.XcxPicService;
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

/**
 * Created by XCA on 2017/5/31.
 */
@Controller
@RequestMapping(value = "/xcxPic")
public class XcxPicController {

    @Autowired
    private XcxPicService xcxPicService;

    @ModelAttribute
    public XcxPic get(@RequestParam(required = false) String id) {
        XcxPic entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = xcxPicService.get(id);
        }
        if (entity == null) {
            entity = new XcxPic();
        }
        return entity;
    }

    @RequestMapping(value = "/list")
    public String picList(Model model,XcxPic xcxPic,
                          HttpServletResponse response,HttpServletRequest request){
        Page<XcxPic> page =
                xcxPicService.list(new Page<XcxPic>(request,response),xcxPic);
        model.addAttribute("page",page);
        model.addAttribute("xcxPic",xcxPic);
        return "/xcxPic/xcxPicList";
    }

    @RequestMapping(value = "/toAdd")
    public String toAdd(Model model,XcxPic xcxPic){
        model.addAttribute("xcxPic",xcxPic);
        return "/xcxPic/xcxPicAdd";
    }

    @RequestMapping(value = "/saveAdd")
    public String addPic(Model model, XcxPic xcxPic,
                         HttpServletRequest request, HttpServletResponse response){
        model.addAttribute("xcxPic",xcxPic);
        String url = xcxPicService.getUrl(request, response);
        if (url != null && !"".equals(url)) {
            xcxPic.setUrl(url);
            xcxPicService.add(xcxPic);
        }
        return "redirect:/xcxPic/list";
    }

    @RequestMapping(value = "/view")
    public String viewPic(Model model,XcxPic xcxPic){
        model.addAttribute("xcxPic",xcxPic);
        return "/xcxPic/xcxPicView";
    }

    /**
     * 显示预览图片
     */
    @RequestMapping(value = "/showViewImg")
    public void showViewImg(XcxPic xcxPic, Integer i, HttpServletRequest request, HttpServletResponse response){
        InputStream inputStream = null;
        OutputStream writer = null;
        try {
            String[] imgs = xcxPic.getUrl().split(",");
//            for (int j=0;j<imgs.length;j++) {
            inputStream = new FileInputStream(imgs[i]);
            writer = response.getOutputStream();

            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(bytes) ) != -1) {
                writer.write(bytes,0,len);
            }
            inputStream.close();
//            }
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

    @RequestMapping(value = "del")
    public String delPic(Model model,XcxPic xcxPic){
        model.addAttribute("xcxPic",xcxPic);
        xcxPic.setDelFlag("1");
        xcxPicService.update(xcxPic);
        return "redirect:/xcxPic/list";
    }
}
