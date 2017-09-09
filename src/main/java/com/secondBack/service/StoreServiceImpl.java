package com.secondBack.service;

import com.secondBack.dao.StoreDao;
import com.secondBack.entity.Store;
import com.tool.basic.TemplateConfig;
import com.tool.upload.UploadServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.util.Date;

/**
 * Created by XCA on 2017/6/2.
 */
@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreDao storeDao;

    @Override
    public Store get() {
        return storeDao.get();
    }

    @Override
    public void add(Store store) {
    	
    	if(store == null){
    		return ;
    	}
    	
    	if( store.getUrl() == null ||store.getUrl().length() <= 0){
    		return ;
    	}
    	
    	if(store.getUrl().indexOf("\\static\\pic\\") < 0){
    		return;
    	}
    	
    	String url = store.getUrl();
    	
    	store.setUrl(TemplateConfig.getValue("IP")+ url.substring(url.indexOf("\\static\\pic\\"),url.length()-1));
    	
        store.setCreateDate(new Date());
        store.setDelFlag("0");
        storeDao.add(store);
    }
    
    @Override
    public void update(Store store) {
        store.setUpdateDate(new Date());
        storeDao.update(store);
    }

    @Override
    public Store upLoad(Store store, HttpServletRequest request, HttpServletResponse response) {
        MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
        UploadServlet.doPost(mRequest,response);
        String url = mRequest.getAttribute("url").toString();
        if (url != null && !"".equals(url)) {
            store.setUrl(url);
        }
        return store;
    }
}
