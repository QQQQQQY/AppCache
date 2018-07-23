package com.example.com.cachetest.cache;

import android.text.TextUtils;
import android.util.ArrayMap;

import com.raizlabs.android.dbflow.config.FlowManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: qq.yang.
 * @Date: 2018/4/12 0012 16:55.
 * @Description: .
 */
public class DiskCache implements ICache {

    @Override
    @SuppressWarnings("unchecked")
    public boolean save(String key, Object object) {
        boolean result;
        try {
            if (object instanceof ISaveDB) {
                // save to DB
                ISaveDB saveDB = (ISaveDB) object;
                ISaveDB s = saveDB.queryData(saveDB.getPrimaryKey());
                if (null != s) {
                    result = s.updateData(saveDB);
                } else {
                    result = saveDB.saveData(saveDB);
                }
                // save the bean's primary key to sp , use when query.
                SpUtil.putData(key, saveDB.getPrimaryKey());
            } else {
                // save to sp
                result = SpUtil.putData(key, object);
            }
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T get(String key, Class<T> clz) {
        T t;
        try {
            String type = clz.getSimpleName();
            switch (type) {
                case "Boolean":
                    t = (T) SpUtil.getData(key, false);
                    break;
                case "Long":
                    t = (T) SpUtil.getData(key, 0L);
                    break;
                case "Float":
                    t = (T) SpUtil.getData(key, 0f);
                    break;
                case "String":
                    t = (T) SpUtil.getData(key, "");
                    break;
                case "Integer":
                    t = (T) SpUtil.getData(key, 0);
                    break;
                case "Double":
                    t = (T) SpUtil.getData(key, "");
                    break;
                default:
                    t = clz.newInstance();
                    if (t instanceof ISaveDB) {
                        // get from DB
                        String primaryKey = (String) SpUtil.getData(key, "");
                        if (!TextUtils.isEmpty(primaryKey)) {
                            ISaveDB saveDB = (ISaveDB) t;
                            t = (T) saveDB.queryData(primaryKey);
                        } else {
                            t = null;
                        }
                    } else {
                        // get from sp
                        t = (T) SpUtil.getData(key, t);
                    }
                    break;
            }
        } catch (Exception e) {
            t = null;
            e.printStackTrace();
        }
        return t;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> boolean saveList(String key, List<T> list) {
        boolean result = true;
        try {
            if (null != list && list.size() > 0) {
                result = true;
                T t0 = list.get(0);
                if (t0 instanceof ISaveDB) {
                    // save to DB
                    ArrayList<String> primaryKeys = new ArrayList<>();
                    for (int i = 0; i < list.size(); i++) {
                        ISaveDB saveDB = (ISaveDB) list.get(i);
                        ISaveDB s = saveDB.queryData(saveDB.getPrimaryKey());
                        if (null != s) {
                            result = s.updateData(saveDB);
                        } else {
                            result = saveDB.saveData(saveDB);
                        }
                        primaryKeys.add(saveDB.getPrimaryKey());
                    }
                    SpUtil.putListData(key, primaryKeys);
                    return result;
                } else {
                    // save to sp
                    result = SpUtil.putListData(key, list);
                }
            }
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> List<T> getList(String key, Class<T> clz) {
        List<T> result;
        try {
            T t = clz.newInstance();
            if (t instanceof ISaveDB) {
                // get from DB
                result = new ArrayList<>();
                List<String> primaryKeys = SpUtil.getListData(key, String.class);
                ISaveDB saveDB = (ISaveDB) t;
                List<ISaveDB> dbAll = saveDB.getAll();
                for (int i = 0; i < dbAll.size(); i++) {
                    ISaveDB dbBean = dbAll.get(i);
                    if (primaryKeys.contains(dbBean.getPrimaryKey())) {
                        result.add((T) dbBean);
                    }
                }
            } else {
                // get from sp
                result = SpUtil.getListData(key, clz);
            }
        } catch (Exception e) {
            result = null;
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public <K, V> boolean saveMap(String key, Map<K, V> map) {
        return SpUtil.putMapData(key, map);
    }

    @Override
    public <V> ArrayMap<String, V> getMap(String key, Class<V> clz) {
        return SpUtil.getMapData(key, clz);
    }

    @Override
    public void clear(String key, Class... dbBeanType) {
        // delete form db.
        if (null != dbBeanType && dbBeanType.length > 0) {
            ISaveDB saveDB = (ISaveDB) get(key, dbBeanType[0]);
            if (null != saveDB) {
                saveDB.deleteData(saveDB.getPrimaryKey());
            } else {
                List<ISaveDB> dbList = getList(key, dbBeanType[0]);
                if (null != dbList) {
                    for (int i = 0; i < dbList.size(); i++) {
                        ISaveDB db = dbList.get(i);
                        db.deleteData(db.getPrimaryKey());
                    }
                }
                SpUtil.putListData(key, null);
            }
        } else {
            // delete form sp.
            SpUtil.putData(key, null);
            SpUtil.putListData(key, null);
            SpUtil.putMapData(key, null);
        }
    }

    @Override
    public void clearAll(String... dbName) {
        if (null != dbName && dbName.length > 0) {
            FlowManager.getDatabase(dbName[0]).destroy();
        } else {
            SpUtil.clear();
        }
    }
}
