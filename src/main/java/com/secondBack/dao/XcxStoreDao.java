package com.secondBack.dao;

import com.secondBack.entity.XcxStore;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by XCA on 2017/5/23.
 */
@Repository
public interface XcxStoreDao {

    XcxStore get(@Param(value = "id")String id);

    List<XcxStore> getGoods(XcxStore xcxStore);

    void add(XcxStore xcxStore);

    void update(XcxStore xcxStore);
}
