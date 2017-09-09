package com.secondBack.service;

import com.secondBack.dao.SysUserDao;
import com.secondBack.entity.SysUser;
import com.tool.basic.MD5Servlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by XCA on 2017/5/23.
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    public boolean login(String username, String password) {
        String pwd = MD5Servlet.md5(password);
        SysUser sysUser = sysUserDao.login(username, pwd);
        if (sysUser != null) {
            return true;
        }
        return false;
    }

    public SysUser getUser(String id) {
        return sysUserDao.getUser(id);
    }
}
