package com.secondBack.dao;

import com.secondBack.entity.XcxUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by XCA on 2017/5/25.
 */
@Repository
public interface XcxUserDao {

    XcxUser get(@Param(value = "id")String id);

    XcxUser getByOpenId(@Param(value = "openId")String openId);

    List<XcxUser> getList(XcxUser xcxUser);

    void update(XcxUser xcxUser);
}
