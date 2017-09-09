package com.secondBack.service;

import com.secondBack.entity.XcxPayLog;
import com.tool.page.Page;

import java.util.List;

/**
 * Created by Administrator on 2017/6/13.
 */
public interface XcxPayLogService {

    XcxPayLog get(String id);

    Page<XcxPayLog> list(Page<XcxPayLog> page,XcxPayLog xcxPayLog);

    XcxPayLog addInfo(XcxPayLog xcxPayLog);

    void update(XcxPayLog xcxPayLog);

}
