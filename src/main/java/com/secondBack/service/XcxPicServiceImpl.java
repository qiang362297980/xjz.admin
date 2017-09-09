package com.secondBack.service;

import com.secondBack.dao.XcxPicDao;
import com.secondBack.entity.XcxPic;
import com.tool.page.Page;
import com.tool.upload.UploadServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by XCA on 2017/5/31.
 */
@Service
public class XcxPicServiceImpl implements XcxPicService {

    @Autowired
    private XcxPicDao xcxPicDao;

    @Override
    public XcxPic get(String id) {
        return xcxPicDao.get(id);
    }

    @Override
    public Page<XcxPic> list(Page<XcxPic> page, XcxPic xcxPic) {
        xcxPic.setPage(page);
        page.setList(xcxPicDao.getList(xcxPic));
        return page;
    }

    @Override
    public void add(XcxPic xcxPic) {
        xcxPic.setCreateDate(new Date());
        xcxPic.setDelFlag("0");
        xcxPicDao.add(xcxPic);
    }

    @Override
    public void update(XcxPic xcxPic) {
        xcxPic.setUpdateDate(new Date());
        xcxPicDao.update(xcxPic);
    }

    @Override
    public String getUrl(HttpServletRequest request, HttpServletResponse response) {
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest)request;
        UploadServlet.doPost(mRequest,response);
        String url = mRequest.getAttribute("url").toString();
        return url;
    }
}
