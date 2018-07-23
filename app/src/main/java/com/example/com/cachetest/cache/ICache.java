package com.example.com.cachetest.cache;

import android.util.ArrayMap;

import java.util.List;
import java.util.Map;

/**
 * @Author: qq.yang.
 * @Date: 2018/4/12 0012 17:15.
 * @Description: .
 */
public interface ICache {

    boolean save(String key, Object object);

    <T> T get(String key, Class<T> clz);

    <T> boolean saveList(String key, List<T> list);

    <T> List<T> getList(String key, Class<T> clz);

    <K, V> boolean saveMap(String key, Map<K, V> map);

    <V> ArrayMap<String, V> getMap(String key, Class<V> clz);

    void clear(String key, Class... dbBeanType);

    void clearAll(String... dbName);

}
