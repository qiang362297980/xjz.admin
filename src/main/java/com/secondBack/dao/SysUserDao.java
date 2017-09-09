package com.secondBack.dao;

import com.secondBack.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by XCA on 2017/5/23.
 */
@Repository
public interface SysUserDao {

    SysUser login(@Param(value = "username")String username, @Param(value = "password")String password);

    SysUser getUser(@Param(value = "id")String id);

}
