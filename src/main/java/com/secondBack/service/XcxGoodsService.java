package com.secondBack.service;


import com.secondBack.entity.XcxGoods;
import com.tool.page.Page;

/**
 * Created by XCA on 2017/5/22.
 */
public interface XcxGoodsService {

    XcxGoods get(String id);

    Page<XcxGoods> getGoods(Page<XcxGoods> page,XcxGoods xcxGoods);

    void update(XcxGoods xcxGoods);
}
