package com.secondBack.service;

import com.secondBack.dao.XcxStoreDao;
import com.secondBack.entity.XcxStore;
import com.tool.page.Page;
import com.tool.upload.UploadServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by XCA on 2017/5/23.
 */
@Service
public class XcxStoreServiceImpl implements XcxStoreService {

    @Autowired
    private XcxStoreDao xcxStoreDao;

    public XcxStore get(String id) {
        return xcxStoreDao.get(id);
    }

    public Page<XcxStore> getGoods(Page<XcxStore> page, XcxStore xcxStore) {
        xcxStore.setPage(page);
        page.setList(xcxStoreDao.getGoods(xcxStore));
        return page;
    }

    public void add(XcxStore xcxStore) {
        xcxStore.setCreateDate(new Date());
        xcxStore.setDelFlag("0");
        xcxStoreDao.add(xcxStore);
    }

    public void update(XcxStore xcxStore) {
        xcxStore.setUpdateDate(new Date());
        xcxStoreDao.update(xcxStore);
    }

    public String uploadPic(HttpServletRequest request, HttpServletResponse response) {
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        UploadServlet.doPost(mRequest,response);
        String url = mRequest.getAttribute("url").toString();
        return url;
    }

    public String nameToUrl(String names,HttpServletRequest request,HttpServletResponse response) {
        String savePath = request.getServletContext().getRealPath("/static/pic/");
        String picUrl = "";
        if (names != null && !"".equals(names)) {
            String[] goodsName = names.split(",");
            for (int i=0;i<goodsName.length; i++) {
                picUrl += savePath+goodsName[i]+",";
            }
        }
        return picUrl;
    }
}
