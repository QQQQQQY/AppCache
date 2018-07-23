package com.example.com.cachetest.cache;

import android.util.Log;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.List;

/**
 * @Author: qq.yang.
 * @Date: 2018/4/13 0013 14:19.
 * @Description: .
 */
@Table(database = DBTest.class)
public class DBTestBean extends BaseModel implements ISaveDB<DBTestBean> {
    private static final String TAG = AppCache.TAG;

    @PrimaryKey
    private String id;
    @Column
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DBTestBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public String getPrimaryKey() {
        return id;
    }

    @Override
    public boolean saveData(DBTestBean testBean) {
        boolean result = testBean.save();
        Log.i(TAG, "saveData: " + result);
        return result;
    }

    @Override
    public boolean deleteData(String primaryKey) {
        DBTestBean querySingle = SQLite.select().from(getClass()).where(DBTestBean_Table.id.eq(primaryKey)).querySingle();
        Log.i(TAG, "deleteData: " + (null == querySingle || querySingle.delete()));
        return null == querySingle || querySingle.delete();
    }

    @Override
    public boolean updateData(DBTestBean testBean) {
        boolean result = testBean.update();
        Log.i(TAG, "updateData: " + result);
        return result;
    }

    @Override
    public DBTestBean queryData(String primaryKey) {
        Log.i(TAG, "queryData: " + primaryKey);
        return SQLite.select().from(getClass()).where(DBTestBean_Table.id.eq(primaryKey)).querySingle();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DBTestBean> getAll() {
        Log.i(TAG, "getAll: ");
        List<? extends DBTestBean> queryList = SQLite.select().from(getClass()).queryList();
        return (List<DBTestBean>) queryList;
    }

}
