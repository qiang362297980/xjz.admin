package com.secondBack.controller;

import com.secondBack.entity.XcxSchool;
import com.secondBack.service.XcxSchoolService;
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
 * Created by Administrator on 2017/6/10.
 */
@Controller
@RequestMapping(value = "/school")
public class XcxSchoolController {

    @Autowired
    private XcxSchoolService xcxSchoolService;

    @ModelAttribute
    public XcxSchool get(@RequestParam(required = false) String id) {
        XcxSchool entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = xcxSchoolService.get(id);
        }
        if (entity == null) {
            entity = new XcxSchool();
        }
        return entity;
    }

    @RequestMapping(value = "list")
    public String list(XcxSchool xcxSchool, Model model,
                       HttpServletRequest request, HttpServletResponse response){
        Page<XcxSchool> page =
                xcxSchoolService.list(new Page<XcxSchool>(request,response),xcxSchool);
        model.addAttribute("page",page);
        model.addAttribute("xcxSchool",xcxSchool);
        return "/xcxSchool/xcxSchoolList";
    }

    @RequestMapping(value = "toAdd")
    public String toAdd(XcxSchool xcxSchool, Model model){
        model.addAttribute("xcxSchool",xcxSchool);
        return "/xcxSchool/xcxSchoolAdd";
    }

    @RequestMapping(value = "saveAdd")
    public String saveAdd(XcxSchool xcxSchool, Model model){
        xcxSchoolService.add(xcxSchool);
        model.addAttribute("xcxSchool",xcxSchool);
        return "redirect:/school/list";
    }

    @RequestMapping(value = "toUpdate")
    public String toUpdate(XcxSchool xcxSchool, Model model){
        model.addAttribute("xcxSchool",xcxSchool);
        return "/xcxSchool/xcxSchoolUpdate";
    }

    @RequestMapping(value = "saveUpdate")
    public String saveUpdate(XcxSchool xcxSchool, Model model){
        xcxSchoolService.update(xcxSchool);
        model.addAttribute("xcxSchool",xcxSchool);
        return "redirect:/school/list";
    }

    @RequestMapping(value = "del")
    public String del(XcxSchool xcxSchool, Model model){
        xcxSchool.setDelFlag("1");
        xcxSchoolService.update(xcxSchool);
        model.addAttribute("xcxSchool",xcxSchool);
        return "redirect:/school/list";
    }
}
