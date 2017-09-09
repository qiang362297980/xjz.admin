package com.secondBack.service;

import com.secondBack.dao.XcxSchoolDao;
import com.secondBack.entity.XcxSchool;
import com.tool.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Administrator on 2017/6/10.
 */
@Service
public class XcxSchoolServiceImpl implements XcxSchoolService {

    @Autowired
    private XcxSchoolDao xcxSchoolDao;

    @Override
    public XcxSchool get(String id) {
        return xcxSchoolDao.get(id);
    }

    @Override
    public Page<XcxSchool> list(Page<XcxSchool> page, XcxSchool xcxSchool) {
        xcxSchool.setPage(page);
        page.setList(xcxSchoolDao.list(xcxSchool));
        return page;
    }

    @Override
    public void add(XcxSchool xcxSchool) {
        xcxSchool.setCreateDate(new Date());
        xcxSchool.setDelFlag("0");
        xcxSchoolDao.add(xcxSchool);
    }

    @Override
    public void update(XcxSchool xcxSchool) {
        xcxSchool.setUpdateDate(new Date());
        xcxSchoolDao.update(xcxSchool);
    }
}
