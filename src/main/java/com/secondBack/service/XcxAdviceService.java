package com.secondBack.service;


import com.secondBack.entity.XcxAdvice;
import com.tool.page.Page;

/**
 * Created by XCA on 2017/5/26.
 */
public interface XcxAdviceService {

    XcxAdvice get(String id);

    Page<XcxAdvice> getList(Page<XcxAdvice> page,XcxAdvice xcxAdvice);

    void update(XcxAdvice xcxAdvice);

}
