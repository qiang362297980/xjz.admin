package com.secondBack.service;

import com.secondBack.entity.XcxStore;
import com.tool.page.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by XCA on 2017/5/23.
 */
public interface XcxStoreService {

    XcxStore get(String id);

    Page<XcxStore> getGoods(Page<XcxStore> page, XcxStore xcxStore);

    void add(XcxStore xcxStore);

    void update(XcxStore xcxStore);

    String uploadPic(HttpServletRequest request, HttpServletResponse response);

    String nameToUrl(String names,HttpServletRequest request,HttpServletResponse response);
}
