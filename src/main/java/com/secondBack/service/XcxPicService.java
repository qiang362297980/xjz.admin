package com.secondBack.service;

import com.secondBack.entity.XcxPic;
import com.tool.page.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by XCA on 2017/5/31.
 */
public interface XcxPicService {

    XcxPic get(String id);

    Page<XcxPic> list(Page<XcxPic> page,XcxPic xcxPic);

    void add(XcxPic xcxPic);

    void update(XcxPic xcxPic);

    String getUrl(HttpServletRequest request, HttpServletResponse response);
}
