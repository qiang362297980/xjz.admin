package com.secondBack.service;

import com.secondBack.entity.XcxUser;
import com.tool.page.Page;

/**
 * Created by XCA on 2017/5/25.
 */
public interface XcxUserService {

    XcxUser get(String id);

    Page<XcxUser> getList(Page<XcxUser> page,XcxUser xcxUser);

    void update(XcxUser xcxUser);

    void doBlankDan(XcxUser xcxUser);
}
