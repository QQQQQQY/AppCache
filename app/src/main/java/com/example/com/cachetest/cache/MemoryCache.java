package com.example.com.cachetest.cache;

import android.util.ArrayMap;

import java.util.List;
import java.util.Map;

/**
 * @Author: qq.yang.
 * @Date: 2018/4/12 0012 16:55.
 * @Description: .
 */
public class MemoryCache implements ICache {
    private static ArrayMap<String, Object> arrayMap = new ArrayMap<>();

    @Override
    public boolean save(String key, Object object) {
        boolean result;
        try {
            arrayMap.put(key, object);
            result = true;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T get(String key, Class<T> clz) {
        T cache;
        try {
            cache = (T) arrayMap.get(key);
        } catch (Exception e) {
            cache = null;
        }
        return cache;
    }

    @Override
    public <T> boolean saveList(String key, List<T> list) {
        return save(key, list);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> getList(String key, Class<T> clz) {
        List<T> result;
        try {
            result = (List<T>) arrayMap.get(key);
        } catch (Exception e) {
            result = null;
        }
        return result;
    }

    @Override
    public <K, V> boolean saveMap(String key, Map<K, V> map) {
        return save(key, map);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <V> ArrayMap<String, V> getMap(String key, Class<V> clz) {
        ArrayMap<String, V> result;
        try {
            result = (ArrayMap<String, V>) arrayMap.get(key);
        } catch (Exception e) {
            result = null;
        }
        return result;
    }

    @Override
    public void clear(String key, Class... dbBeanType) {
        arrayMap.remove(key);
    }

    @Override
    public void clearAll(String... dbName) {
        arrayMap.clear();
    }

}
