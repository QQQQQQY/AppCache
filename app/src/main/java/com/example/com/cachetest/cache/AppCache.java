package com.example.com.cachetest.cache;

import android.util.ArrayMap;
import android.util.Log;

import java.util.List;
import java.util.Map;

/**
 * @Author: qq.yang.
 * @Date: 2018/4/12 0012 16:55.
 * @Description: .
 */
public class AppCache implements ICache {

    public static final String TAG = "AppCache";

    private static ICache memoryCache = new MemoryCache();
    private static ICache diskCache = new DiskCache();

    private static AppCache appCache;

    private boolean isOnlyMemory = false;
    private boolean isOnlyDisk = false;

    private AppCache() {
    }

    public static synchronized AppCache getInstance() {
        if (null == appCache) {
            appCache = new AppCache();
        }
        appCache.isOnlyMemory = false;
        appCache.isOnlyDisk = false;
        return appCache;
    }

    public ICache getMemoryCache() {
        return memoryCache;
    }

    public void setMemoryCache(ICache memoryCache) {
        AppCache.memoryCache = memoryCache;
    }

    public ICache getDiskCache() {
        return diskCache;
    }

    public void setDiskCache(ICache diskCache) {
        AppCache.diskCache = diskCache;
    }

    public AppCache setOnlyMemoryCache() {
        isOnlyMemory = true;
        isOnlyDisk = false;
        return appCache;
    }

    public AppCache setOnlyDiskCache() {
        isOnlyDisk = true;
        isOnlyMemory = false;
        return appCache;
    }

    public boolean save(String key, Object value) {
        boolean result;
        if (isOnlyMemory) {
            result = memoryCache.save(key, value);
        } else if (isOnlyDisk) {
            result = diskCache.save(key, value);
        } else {
            boolean memoryCacheResult = memoryCache.save(key, value);
            boolean diskCacheResult = diskCache.save(key, value);
            Log.d(TAG, "\nCache Object\n" + "key = " + key + "\n" + "value = " + value.toString()
                    + "\nmemoryCacheResult : " + memoryCacheResult + "\ndiskCacheResult : " + diskCacheResult);
            result = memoryCacheResult && diskCacheResult;
        }
        return result;
    }

    public <T> T get(String key, Class<T> clz) {
        T cache;
        if (isOnlyMemory) {
            cache = memoryCache.get(key, clz);
            Log.i(TAG, "\nget object from memory : \nkey = " + key + "\nvalue = " + (null == cache ? "null" : cache));
        } else if (isOnlyDisk) {
            cache = diskCache.get(key, clz);
            Log.w(TAG, "\nget object from disk : \nkey = " + key + "\nvalue = " + (null == cache ? "null" : cache));
        } else {
            cache = memoryCache.get(key, clz);
            if (null != cache) {
                Log.i(TAG, "\nget object from memory : \nkey = " + key + "\nvalue = " + cache);
                return cache;
            } else {
                cache = diskCache.get(key, clz);
//            memoryCache.save(key, cache);
                Log.w(TAG, "\nget object from disk : \nkey = " + key + "\nvalue = " + (null == cache ? "null" : cache));
            }
        }
        return cache;
    }

    @Override
    public <T> boolean saveList(String key, List<T> list) {
        boolean result;
        if (isOnlyMemory) {
            result = memoryCache.saveList(key, list);
        } else if (isOnlyDisk) {
            result = diskCache.saveList(key, list);
        } else {
            boolean memoryCacheResult = memoryCache.saveList(key, list);
            boolean diskCacheResult = diskCache.saveList(key, list);
            Log.d(TAG, "\nCache List\n" + "key = " + key + "\n" + "value = " + list.toString()
                    + "\nmemoryCacheResult : " + memoryCacheResult + "\ndiskCacheResult : " + diskCacheResult);
            result = memoryCacheResult && diskCacheResult;
        }
        return result;
    }

    @Override
    public <T> List<T> getList(String key, Class<T> clz) {
        List<T> cache;
        if (isOnlyMemory) {
            cache = memoryCache.getList(key, clz);
            Log.i(TAG, "\nget list from memory : \nkey = " + key + "\nvalue = " + (null == cache ? "null" : cache));
        } else if (isOnlyDisk) {
            cache = diskCache.getList(key, clz);
            Log.w(TAG, "\nget list from disk : \nkey = " + key + "\nvalue = " + (null == cache ? "null" : cache));
        } else {
            cache = memoryCache.getList(key, clz);
            if (null != cache) {
                Log.i(TAG, "\nget list from memory : \nkey = " + key + "\nvalue = " + cache);
                return cache;
            }
            cache = diskCache.getList(key, clz);
//            memoryCache.saveList(key, cache);
            Log.w(TAG, "\nget list from disk : \nkey = " + key + "\nvalue = " + (null == cache ? "null" : cache));
        }
        return cache;
    }

    @Override
    public <K, V> boolean saveMap(String key, Map<K, V> map) {
        boolean result;
        if (isOnlyMemory) {
            result = memoryCache.saveMap(key, map);
        } else if (isOnlyDisk) {
            result = diskCache.saveMap(key, map);
        } else {
            boolean memoryCacheResult = memoryCache.saveMap(key, map);
            boolean diskCacheResult = diskCache.saveMap(key, map);
            Log.d(TAG, "\nCache Map\n" + "key = " + key + "\n" + "value = " + map.toString()
                    + "\nmemoryCacheResult : " + memoryCacheResult + "\ndiskCacheResult : " + diskCacheResult);
            result = memoryCacheResult && diskCacheResult;
        }
        return result;
    }

    @Override
    public <V> ArrayMap<String, V> getMap(String key, Class<V> clz) {
        ArrayMap<String, V> cache;
        if (isOnlyMemory) {
            cache = memoryCache.getMap(key, clz);
            Log.i(TAG, "\nget map from memory : \nkey = " + key + "\nvalue = " + (null == cache ? "null" : cache));
        } else if (isOnlyDisk) {
            cache = diskCache.getMap(key, clz);
            Log.w(TAG, "\nget map from disk : \nkey = " + key + "\nvalue = " + (null == cache ? "null" : cache));
        } else {
            cache = memoryCache.getMap(key, clz);
            if (null != cache) {
                Log.i(TAG, "\nget map from memory : \nkey = " + key + "\nvalue = " + cache);
                return cache;
            }
            cache = diskCache.getMap(key, clz);
//            memoryCache.saveMap(key, cache);
            Log.w(TAG, "\nget map from disk : \nkey = " + key + "\nvalue = " + (null == cache ? "null" : cache));
        }
        return cache;
    }

    @Override
    public void clear(String key, Class... dbBeanType) {
        memoryCache.clear(key);
        diskCache.clear(key, dbBeanType);
    }

    @Override
    public void clearAll(String... dbName) {
        memoryCache.clearAll();
        diskCache.clearAll(dbName);
    }

}
