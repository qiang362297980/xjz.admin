package com.secondBack.controller;

import com.secondBack.entity.XcxGoods;
import com.secondBack.service.XcxGoodsService;
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
 * Created by XCA on 2017/5/23.
 */
@Controller
@RequestMapping(value = "/xcxGoods")
public class XcxGoodsController {

    @Autowired
    private XcxGoodsService xcxGoodsService;

    @ModelAttribute
    public XcxGoods get(@RequestParam(required = false) String id) {
        XcxGoods entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = xcxGoodsService.get(id);
        }
        if (entity == null) {
            entity = new XcxGoods();
        }
        return entity;
    }

    @RequestMapping(value = "/list")
    public String getGoodsList(Model model, XcxGoods xcxGoods,
                               HttpServletRequest request, HttpServletResponse response){
        Page<XcxGoods> page =
                xcxGoodsService.getGoods(new Page<XcxGoods>(request,response),xcxGoods);
        model.addAttribute("page",page);
        model.addAttribute("xcxGoods",xcxGoods);
        return "/xcxGoods/xcxGoodsList";
    }

    @RequestMapping(value = "/toView")
    public String toView(Model model,XcxGoods xcxGoods){
        model.addAttribute("xcxGoods",xcxGoods);
        return "/xcxGoods/xcxGoodsView";
    }

    /**
     * 显示图片
     */
    @RequestMapping(value = "/showImg")
    public void showImg(XcxGoods xcxGoods,Integer i,HttpServletRequest request,HttpServletResponse response){
        InputStream inputStream = null;
        OutputStream writer = null;
        try {
            String[] imgs = xcxGoods.getUrl().split(",");
            List<String> newImgs = new ArrayList<String>();
            for (String img : imgs) {
                img = img.replaceAll("\\\\", "/");
                newImgs.add(img);
            }
            inputStream = new FileInputStream(newImgs.get(i));
            writer = response.getOutputStream();

            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(bytes) ) != -1) {
                writer.write(bytes,0,len);
            }
            inputStream.close();
            System.out.println(imgs[i]+" end");
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

    @RequestMapping(value = "/saveView")
    public String saveView(Model model,XcxGoods xcxGoods){
        model.addAttribute("xcxGoods",xcxGoods);
        xcxGoodsService.update(xcxGoods);
        return "redirect:/xcxGoods/list";
    }


}
