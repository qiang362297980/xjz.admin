package com.secondBack.controller;

import com.secondBack.entity.XcxAdvice;
import com.secondBack.service.XcxAdviceService;
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

/**
 * Created by XCA on 2017/5/26.
 */
@Controller
@RequestMapping(value = "/xcxAdvice")
public class XcxAdviceController {

    @Autowired
    private XcxAdviceService xcxAdviceService;

    @ModelAttribute
    public XcxAdvice get(@RequestParam(required = false) String id) {
        XcxAdvice entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = xcxAdviceService.get(id);
        }
        if (entity == null) {
            entity = new XcxAdvice();
        }
        return entity;
    }

    /**
     * list
     */
    @RequestMapping(value = "/list")
    public String list(Model model, XcxAdvice xcxAdvice,
                       HttpServletRequest request, HttpServletResponse response){
        Page<XcxAdvice> page =
                xcxAdviceService.getList(new Page<XcxAdvice>(request,response),xcxAdvice);
        model.addAttribute("page",page);
        model.addAttribute("xcxAdvice",xcxAdvice);
        return "/xcxAdvice/xcxAdviceList";
    }

    @RequestMapping(value = "/view")
    public String view(Model model,XcxAdvice xcxAdvice){
        model.addAttribute("xcxAdvice",xcxAdvice);
        return "/xcxAdvice/xcxAdviceView";
    }

    @RequestMapping(value = "/del")
    public String del(Model model,XcxAdvice xcxAdvice){
        model.addAttribute("xcxAdvice",xcxAdvice);
        xcxAdvice.setDelFlag("1");
        xcxAdviceService.update(xcxAdvice);
        return "redirect:/xcxAdvice/list";
    }

}
