package com.secondBack.controller;

import com.secondBack.entity.XcxStore;
import com.secondBack.service.XcxStoreService;
import com.tool.basic.StringUtils;
import com.tool.basic.TemplateConfig;
import com.tool.page.Page;
import com.tool.upload.UploadServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 商店管理
 */
@Controller
@RequestMapping(value = "/xcxStore")
public class XcxStoreController {

    @Autowired
    private XcxStoreService xcxStoreService;

    @ModelAttribute
    public XcxStore get(@RequestParam(required = false) String id) {
        XcxStore entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = xcxStoreService.get(id);
        }
        if (entity == null) {
            entity = new XcxStore();
        }
        return entity;
    }

    @RequestMapping(value = "/list")
    public String getStoreGoodsList(Model model, XcxStore xcxStore,
                                    HttpServletRequest request, HttpServletResponse response){
        Page<XcxStore> page =
                xcxStoreService.getGoods(new Page<XcxStore>(request,response),xcxStore);
        model.addAttribute("page",page);
        model.addAttribute("xcxStore",xcxStore);
        return "/xcxStore/storeGoodsList";
    }

    @RequestMapping(value = "/toAdd")
    public String toAddGoods(Model model,XcxStore xcxStore){
        model.addAttribute("xcxStore",xcxStore);
        return "/xcxStore/storeGoodsAdd";
    }

    @RequestMapping(value = "/saveAdd")
    public String saveGoods(Model model,XcxStore xcxStore,
                            HttpServletRequest request,HttpServletResponse response){
        model.addAttribute("xcxStore",xcxStore);
        String viewPicUrl = xcxStoreService.uploadPic(request, response);
        if (viewPicUrl != null && !"".equals(viewPicUrl)) {
            xcxStore.setViewPicUrl(viewPicUrl);
        }
        xcxStore.setPicUrl(xcxStoreService.nameToUrl(xcxStore.getPicUrl(),request,response));
        xcxStoreService.add(xcxStore);
        return "redirect:/xcxStore/list";
    }

    @RequestMapping(value = "/toUpdate")
    public String toUpdate(Model model,XcxStore xcxStore){
        model.addAttribute("xcxStore",xcxStore);
        return "/xcxStore/storeGoodsUpdate";
    }

    /**
     * 显示商品图片
     */
    @RequestMapping(value = "/showGoodsImg")
    public void showImg(XcxStore xcxStore,Integer i,HttpServletRequest request,HttpServletResponse response){
        InputStream inputStream = null;
        OutputStream writer = null;
        try {
            String[] imgs = xcxStore.getPicUrl().split(",");
//            for (int j=0;j<imgs.length;j++) {
            inputStream = new FileInputStream(imgs[i]);
            writer = response.getOutputStream();

            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(bytes) ) != -1) {
                writer.write(bytes,0,len);
            }
            inputStream.close();
            System.out.println(imgs[i]+" end");
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

    @RequestMapping(value = "/saveUpdate")
    public String saveUpdate(Model model,XcxStore xcxStore,
                             HttpServletRequest request,HttpServletResponse response){
        model.addAttribute("xcxStore",xcxStore);
        String viewPicUrl = xcxStoreService.uploadPic(request, response);
        if (viewPicUrl != null && !"".equals(viewPicUrl)) {
            xcxStore.setViewPicUrl(viewPicUrl);
        }
        String picUrl = xcxStoreService.nameToUrl(xcxStore.getPicUrl(),request,response);
        if (picUrl != null && !"".equals(picUrl)) {
            xcxStore.setPicUrl(picUrl);
        }
        xcxStoreService.update(xcxStore);
        return "redirect:/xcxStore/list";
    }

    @RequestMapping(value = "delUse")
    public String delOrUser(Model model,XcxStore xcxStore,String delFlag){
        model.addAttribute("xcxStore",xcxStore);
        xcxStore.setDelFlag(delFlag);
        xcxStoreService.update(xcxStore);
        return "redirect:/xcxStore/list";
    }

    /**
     * dropzone上传
     */
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public String dropzoneUpload(HttpServletRequest request,HttpServletResponse response){
        return xcxStoreService.uploadPic(request,response);
    }

}
