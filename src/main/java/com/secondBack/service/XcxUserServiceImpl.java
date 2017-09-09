package com.secondBack.service;

import com.secondBack.dao.XcxUserDao;
import com.secondBack.entity.XcxUser;
import com.tool.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by XCA on 2017/5/25.
 */
@Service
public class XcxUserServiceImpl implements XcxUserService {

    @Autowired
    private XcxUserDao xcxUserDao;

    public XcxUser get(String id) {
        return xcxUserDao.get(id);
    }

    public Page<XcxUser> getList(Page<XcxUser> page, XcxUser xcxUser) {
        xcxUser.setPage(page);
        page.setList(xcxUserDao.getList(xcxUser));
        return page;
    }

    public void update(XcxUser xcxUser) {
        xcxUser.setUpdateDate(new Date());
        xcxUserDao.update(xcxUser);
    }

    public void doBlankDan(XcxUser xcxUser) {
        if ("1".equals(xcxUser.getStatus())) {
            xcxUser.setStatus("0");
        } else if ("0".equals(xcxUser.getStatus())) {
            xcxUser.setStatus("1");
        }
        xcxUser.setUpdateDate(new Date());
        xcxUserDao.update(xcxUser);
    }
}
