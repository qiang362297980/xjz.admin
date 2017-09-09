package com.secondBack.service;

import com.secondBack.entity.SysUser;

/**
 * Created by XCA on 2017/5/23.
 */
public interface SysUserService {

    boolean login(String username,String password);

    SysUser getUser(String id);

}
