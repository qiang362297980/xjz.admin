package com.secondBack.service;

import com.secondBack.dao.XcxPayLogDao;
import com.secondBack.dao.XcxStoreDao;
import com.secondBack.dao.XcxUserDao;
import com.secondBack.entity.XcxPayLog;
import com.secondBack.entity.XcxStore;
import com.secondBack.entity.XcxUser;
import com.tool.page.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/6/13.
 */
@Service
public class XcxPayLogServiceImpl implements XcxPayLogService {

    @Autowired
    private XcxPayLogDao xcxPayLogDao;

    @Autowired
    private XcxUserDao xcxUserDao;

    @Autowired
    private XcxStoreDao xcxStoreDao;

    @Override
    public XcxPayLog get(String id) {
        return xcxPayLogDao.get(id);
    }

    @Override
    public Page<XcxPayLog> list(Page<XcxPayLog> page, XcxPayLog xcxPayLog) {
    	
        xcxPayLog.setPage(page);
        List<XcxPayLog> list = xcxPayLogDao.list(xcxPayLog);
        if (list == null) {
        	return null; 
        }
        XcxUser xcxUser = null;
        XcxStore xcxStore = null;
        
            for (XcxPayLog payLog : list) {
                xcxUser = xcxUserDao.getByOpenId(payLog.getOpenId());
                
                if(xcxUser == null){
                	continue;
                }
                
                xcxStore = xcxStoreDao.get(payLog.getGoodsId());
                
                if(xcxStore == null){
                	continue;
                }
                
                payLog.setUserName(xcxUser.getUserName());
                payLog.setArea(xcxUser.getArea());
                payLog.setGoodsUrl(xcxStore.getViewPicUrl());
            }
            
        page.setList(list);
        return page;
    }

    @Override
    public XcxPayLog addInfo(XcxPayLog xcxPayLog) {
    	
    	if(xcxPayLog == null){
    		return null;
    	}
    	
        XcxUser xcxUser = xcxUserDao.getByOpenId(xcxPayLog.getOpenId());
        
        if(xcxUser == null){
        	return xcxPayLog;
        }
        
        XcxStore xcxStore = xcxStoreDao.get(xcxPayLog.getGoodsId());
        
        if(xcxStore == null){
        	return xcxPayLog;
        }
        String goodsUrl = null;
        
        
        if(xcxStore.getViewPicUrl() !=  null && 
        		xcxStore.getViewPicUrl().length() > 0 && 
        		xcxStore.getViewPicUrl().indexOf(",") > 0){
        	
        	goodsUrl = xcxStore.getViewPicUrl().split(",")[0];
        }
        
        xcxPayLog.setUserName(xcxUser.getUserName());
        xcxPayLog.setArea(xcxUser.getArea());
        xcxPayLog.setGoodsUrl(goodsUrl);
        return xcxPayLog;
    }

    @Override
    public void update(XcxPayLog xcxPayLog) {
        xcxPayLog.setUpdateDate(new Date());
        xcxPayLogDao.update(xcxPayLog);
    }
}
