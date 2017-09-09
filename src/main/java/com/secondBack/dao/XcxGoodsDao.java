package com.secondBack.dao;

import com.secondBack.entity.XcxGoods;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by XCA on 2017/5/22.
 */
@Repository
public interface XcxGoodsDao {

    XcxGoods get(@Param(value = "id") String id);

    List<XcxGoods> getGoods(XcxGoods xcxGoods);

    void update(XcxGoods xcxGoods);
}
