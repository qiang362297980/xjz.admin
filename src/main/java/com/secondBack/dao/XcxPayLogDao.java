package com.secondBack.dao;

import com.secondBack.entity.XcxPayLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/6/13.
 */
@Repository
public interface XcxPayLogDao {

    XcxPayLog get(@Param(value = "id")String id);

    List<XcxPayLog> list(XcxPayLog xcxPayLog);

    void update(XcxPayLog xcxPayLog);

}
