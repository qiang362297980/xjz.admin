package com.secondBack.dao;

import com.secondBack.entity.Store;
import org.springframework.stereotype.Repository;

/**
 * Created by XCA on 2017/6/2.
 */
@Repository
public interface StoreDao {

    Store get();

    void add(Store store);

    void update(Store store);

}
