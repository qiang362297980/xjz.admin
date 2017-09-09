package com.secondBack.controller;

import com.secondBack.entity.XcxUser;
import com.secondBack.service.XcxUserService;
import com.tool.basic.StringUtils;
import com.tool.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用户控制层
 */
@Controller
@RequestMapping(value = "/xcxUser")
public class XcxUserController {

    @Autowired
    private XcxUserService xcxUserService;

    @ModelAttribute
    public XcxUser get(@RequestParam(required = false) String id) {
        XcxUser entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = xcxUserService.get(id);
        }
        if (entity == null) {
            entity = new XcxUser();
        }
        return entity;
    }

    /**
     * list
     */
    @RequestMapping(value = "/list")
    public String list(Model model, XcxUser xcxUser,
                       HttpServletRequest request, HttpServletResponse response) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (xcxUser.getStartTime() != null && !"".equals(xcxUser.getStartTime())) {
            xcxUser.setStartDate(sdf.parse(xcxUser.getStartTime()));
        }
        Page page = xcxUserService.getList(new Page<XcxUser>(request,response),xcxUser);
        model.addAttribute("page",page);
        model.addAttribute("xcxUser",xcxUser);
        return "/xcxUser/xcxUserList";
    }

    /**
     * 黑名单
     */
    @RequestMapping(value = "/blackDan")
    public String addBlack(Model model,XcxUser xcxUser){
        model.addAttribute("xcxUser",xcxUser);
        xcxUserService.doBlankDan(xcxUser);
        return "redirect:/xcxUser/list";
    }
}
