package com.example.com.cachetest.cache;

import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * @Author: qq.yang.
 * @Date: 2018/4/13 0013 14:19.
 * @Description: .
 */
public class SpTestBean extends BaseModel {
    private static final String TAG = AppCache.TAG;

    private String id;
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
}
