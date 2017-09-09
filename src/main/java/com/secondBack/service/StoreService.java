package com.secondBack.service;

import com.secondBack.entity.Store;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by XCA on 2017/6/2.
 */
public interface StoreService {

    Store get();

    void add(Store store);

    void update(Store store);

    Store upLoad(Store store, HttpServletRequest request, HttpServletResponse response);
}
