package com.secondBack.dao;

import com.secondBack.entity.XcxAdvice;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by XCA on 2017/5/26.
 */
@Repository
public interface XcxAdviceDao {

    XcxAdvice get(@Param(value = "id") String id);

    List<XcxAdvice> getList(XcxAdvice xcxAdvice);

    void update(XcxAdvice xcxAdvice);
}
