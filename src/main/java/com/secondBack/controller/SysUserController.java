package com.secondBack.controller;

import com.secondBack.entity.SysUser;
import com.secondBack.service.SysUserService;
import com.tool.basic.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by XCA on 2017/5/22.
 */
@Controller
@RequestMapping(value = "/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @ModelAttribute
    public SysUser get(@RequestParam(required = false) String id) {
        SysUser entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = sysUserService.getUser(id);
        }
        if (entity == null) {
            entity = new SysUser();
        }
        return entity;
    }

    @RequestMapping(value = "/login")
    public String login(Model model,SysUser sysUser){
        model.addAttribute("sysUser",sysUser);
        return "login";
    }

    @RequestMapping(value = "/loginCheck")
    public String checkLogin(SysUser sysUser,Model model){
        boolean flag = sysUserService.login(sysUser.getUsername(),sysUser.getPassword());
        if (flag) {
            model.addAttribute("sysUser",sysUser);
            return "main";
        }
        return "redirect:/sysUser/login";
    }

    @RequestMapping(value = "/main")
    public String main(SysUser sysUser,Model model){
        model.addAttribute("sysUser",sysUser);
        return "/main";
    }
}
