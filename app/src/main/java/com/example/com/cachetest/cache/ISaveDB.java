package com.example.com.cachetest.cache;

import java.util.List;

/**
 * @Author: qq.yang.
 * @Date: 2018/4/13 0013 9:35.
 * @Description: .
 */
public interface ISaveDB<T extends ISaveDB> {

    String getPrimaryKey();

    boolean saveData(T t); // add

    boolean deleteData(String primaryKey); // delete

    boolean updateData(T t); // modify

    T queryData(String primaryKey); // query

    List<T> getAll();

}
