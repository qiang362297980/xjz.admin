package com.secondBack.service;

import com.secondBack.dao.XcxAdviceDao;
import com.secondBack.dao.XcxUserDao;
import com.secondBack.entity.XcxAdvice;
import com.secondBack.entity.XcxUser;
import com.tool.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by XCA on 2017/5/26.
 */
@Service
public class XcxAdviceServiceImpl implements XcxAdviceService {

    @Autowired
    private XcxAdviceDao xcxAdviceDao;

    @Autowired
    private XcxUserDao xcxUserDao;

    @Override
    public XcxAdvice get(String id) {
        return xcxAdviceDao.get(id);
    }

    @Override
    public Page<XcxAdvice> getList(Page<XcxAdvice> page, XcxAdvice xcxAdvice) {
        xcxAdvice.setPage(page);
        List<XcxAdvice> list = xcxAdviceDao.getList(xcxAdvice);
        for (XcxAdvice xcxAdvice1 : list) {
            XcxUser xcxUser = xcxUserDao.getByOpenId(xcxAdvice1.getOpenId());
            if (xcxUser != null) {
                xcxAdvice1.setName(xcxUser.getUserName());
            }
        }
        page.setList(list);
        return page;
    }

    @Override
    public void update(XcxAdvice xcxAdvice) {
        xcxAdvice.setUpdateDate(new Date());
        xcxAdviceDao.update(xcxAdvice);
    }
}
