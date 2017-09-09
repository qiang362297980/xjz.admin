package com.secondBack.service;

import com.secondBack.dao.XcxGoodsDao;
import com.secondBack.entity.XcxGoods;
import com.tool.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by XCA on 2017/5/22.
 */
@Service
public class XcxGoodsServiceImpl implements XcxGoodsService{

    @Autowired
    private XcxGoodsDao xcxGoodsDao;

    public XcxGoods get(String id) {
        return xcxGoodsDao.get(id);
    }

    public Page<XcxGoods> getGoods(Page<XcxGoods> page, XcxGoods xcxGoods) {
        xcxGoods.setPage(page);
        page.setList(xcxGoodsDao.getGoods(xcxGoods));
        return page;
    }

    public void update(XcxGoods xcxGoods) {
        xcxGoods.setUpdateDate(new Date());
        xcxGoodsDao.update(xcxGoods);
    }
}
