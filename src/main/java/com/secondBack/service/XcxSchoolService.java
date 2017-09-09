package com.secondBack.service;

import com.secondBack.entity.XcxSchool;
import com.tool.page.Page;

/**
 * Created by Administrator on 2017/6/10.
 */
public interface XcxSchoolService {

    XcxSchool get(String id);

    Page<XcxSchool> list(Page<XcxSchool> page,XcxSchool xcxSchool);

    void add(XcxSchool xcxSchool);

    void update(XcxSchool xcxSchool);

}
