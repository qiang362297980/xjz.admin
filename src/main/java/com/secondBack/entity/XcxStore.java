package com.secondBack.entity;

import com.tool.page.Page;

import java.util.Date;

/**
 * Created by XCA on 2017/5/23.
 */
public class XcxStore {

    private String id;
    private String name;
    private String address;
    private String viewPicUrl;//预览图
    private String picUrl;
    private String price;
    private String oldPrice;
    private String yunFee;
    private String description;
    private int salesVolume;
    private String degree;
    private Date createDate;
    private Date updateDate;
    private String delFlag;

    private Page page;

    public String getYunFee() {
        return yunFee;
    }

    public void setYunFee(String yunFee) {
        this.yunFee = yunFee;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(int salesVolume) {
        this.salesVolume = salesVolume;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public String getViewPicUrl() {
        return viewPicUrl;
    }

    public void setViewPicUrl(String viewPicUrl) {
        this.viewPicUrl = viewPicUrl;
    }
}
