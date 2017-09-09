package com.secondBack.dao;

import com.secondBack.entity.XcxSchool;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/6/3.
 */
@Repository
public interface XcxSchoolDao {

    XcxSchool get(@Param(value = "id")String id);

    List<XcxSchool> list(XcxSchool xcxSchool);

    void add(XcxSchool xcxSchool);

    void update(XcxSchool xcxSchool);
}
