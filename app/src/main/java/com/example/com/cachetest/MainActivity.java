package com.example.com.cachetest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.com.cachetest.cache.AppCache;
import com.example.com.cachetest.cache.DBTestBean;
import com.example.com.cachetest.cache.SpTestBean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_STRING = "string";
    private static final String KEY_INT = "int";
    private static final String KEY_LONG = "long";
    private static final String KEY_FLOAT = "float";
    private static final String KEY_DOUBLE = "double";
    private static final String KEY_BOOLEAN = "boolean";

    private static final String KEY_SP_TEST_BEAN = "spTestBean";
    private static final String KEY_SP_TEST_BEAN_LIST = "spTestBeanList";

    private static final String KEY_DB_TEST_BEAN = "dbTestBean";
    private static final String KEY_DB_TEST_BEAN_LIST = "dbTestBeanList";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testAppCache();
    }

    private void testDelete() {
        Log.e(AppCache.TAG, "start test delete");
        AppCache.getInstance().clear(KEY_STRING);
        AppCache.getInstance().clear(KEY_INT);
        AppCache.getInstance().clear(KEY_LONG);
        AppCache.getInstance().clear(KEY_FLOAT);
        AppCache.getInstance().clear(KEY_DOUBLE);
        AppCache.getInstance().clear(KEY_BOOLEAN);

        AppCache.getInstance().clear(KEY_SP_TEST_BEAN);
        AppCache.getInstance().clear(KEY_SP_TEST_BEAN_LIST);

        AppCache.getInstance().clear(KEY_DB_TEST_BEAN, DBTestBean.class);
        AppCache.getInstance().clear(KEY_DB_TEST_BEAN_LIST, DBTestBean.class);

        AppCache.getInstance().setOnlyMemoryCache().get(KEY_STRING, String.class);
        AppCache.getInstance().setOnlyDiskCache().get(KEY_STRING, String.class);

        AppCache.getInstance().setOnlyMemoryCache().get(KEY_INT, Integer.class);
        AppCache.getInstance().setOnlyDiskCache().get(KEY_INT, Integer.class);

        AppCache.getInstance().setOnlyMemoryCache().get(KEY_LONG, Long.class);
        AppCache.getInstance().setOnlyDiskCache().get(KEY_LONG, Long.class);

        AppCache.getInstance().setOnlyMemoryCache().get(KEY_FLOAT, Float.class);
        AppCache.getInstance().setOnlyDiskCache().get(KEY_FLOAT, Float.class);

        AppCache.getInstance().setOnlyMemoryCache().get(KEY_DOUBLE, Double.class);
        AppCache.getInstance().setOnlyDiskCache().get(KEY_DOUBLE, Double.class);

        AppCache.getInstance().setOnlyMemoryCache().get(KEY_BOOLEAN, Boolean.class);
        AppCache.getInstance().setOnlyDiskCache().get(KEY_BOOLEAN, Boolean.class);


        AppCache.getInstance().setOnlyMemoryCache().get(KEY_SP_TEST_BEAN, SpTestBean.class);
        AppCache.getInstance().setOnlyDiskCache().get(KEY_SP_TEST_BEAN, SpTestBean.class);

        AppCache.getInstance().setOnlyMemoryCache().getList(KEY_SP_TEST_BEAN_LIST, SpTestBean.class);
        AppCache.getInstance().setOnlyDiskCache().getList(KEY_SP_TEST_BEAN_LIST, SpTestBean.class);


        AppCache.getInstance().setOnlyMemoryCache().get(KEY_DB_TEST_BEAN, DBTestBean.class);
        AppCache.getInstance().setOnlyDiskCache().get(KEY_DB_TEST_BEAN, DBTestBean.class);

        AppCache.getInstance().setOnlyMemoryCache().getList(KEY_DB_TEST_BEAN_LIST, DBTestBean.class);
        AppCache.getInstance().setOnlyDiskCache().getList(KEY_DB_TEST_BEAN_LIST, DBTestBean.class);
    }

    private void testAppCache() {
        testBaseDataType();

        testObject();

        testDelete();
    }

    private void testObject() {
        testObjectSpCache();
        testObjectDBCache();
    }

    private void testObjectDBCache() {
        List<DBTestBean> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            DBTestBean bean = new DBTestBean();
            bean.setId(i + "");
            bean.setName(i + "");
            list.add(bean);
        }

        AppCache.getInstance().save(KEY_DB_TEST_BEAN, list.get(0));
        AppCache.getInstance().setOnlyMemoryCache().get(KEY_DB_TEST_BEAN, DBTestBean.class);
        AppCache.getInstance().setOnlyDiskCache().get(KEY_DB_TEST_BEAN, DBTestBean.class);

        AppCache.getInstance().saveList(KEY_DB_TEST_BEAN_LIST, list);
        AppCache.getInstance().setOnlyMemoryCache().getList(KEY_DB_TEST_BEAN_LIST, DBTestBean.class);
        AppCache.getInstance().setOnlyDiskCache().getList(KEY_DB_TEST_BEAN_LIST, DBTestBean.class);
    }

    private void testObjectSpCache() {
        List<SpTestBean> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            SpTestBean bean = new SpTestBean();
            bean.setId(i + "");
            bean.setName(i + "");
            list.add(bean);
        }

        AppCache.getInstance().save(KEY_SP_TEST_BEAN, list.get(0));
        AppCache.getInstance().setOnlyMemoryCache().get(KEY_SP_TEST_BEAN, SpTestBean.class);
        AppCache.getInstance().setOnlyDiskCache().get(KEY_SP_TEST_BEAN, SpTestBean.class);

        AppCache.getInstance().saveList(KEY_SP_TEST_BEAN_LIST, list);
        AppCache.getInstance().setOnlyMemoryCache().getList(KEY_SP_TEST_BEAN_LIST, SpTestBean.class);
        AppCache.getInstance().setOnlyDiskCache().getList(KEY_SP_TEST_BEAN_LIST, SpTestBean.class);
    }

    private void testBaseDataType() {
        AppCache.getInstance().save(KEY_STRING, "sssssssssssss");
        AppCache.getInstance().setOnlyMemoryCache().get(KEY_STRING, String.class);
        AppCache.getInstance().setOnlyDiskCache().get(KEY_STRING, String.class);

        AppCache.getInstance().save(KEY_INT, 11111);
        AppCache.getInstance().setOnlyMemoryCache().get(KEY_INT, Integer.class);
        AppCache.getInstance().setOnlyDiskCache().get(KEY_INT, Integer.class);

        AppCache.getInstance().save(KEY_LONG, 22222L);
        AppCache.getInstance().setOnlyMemoryCache().get(KEY_LONG, Long.class);
        AppCache.getInstance().setOnlyDiskCache().get(KEY_LONG, Long.class);

        AppCache.getInstance().save(KEY_FLOAT, 33333.33f);
        AppCache.getInstance().setOnlyMemoryCache().get(KEY_FLOAT, Float.class);
        AppCache.getInstance().setOnlyDiskCache().get(KEY_FLOAT, Float.class);

        AppCache.getInstance().save(KEY_DOUBLE, 44444.44d);
        AppCache.getInstance().setOnlyMemoryCache().get(KEY_DOUBLE, Double.class);
        AppCache.getInstance().setOnlyDiskCache().get(KEY_DOUBLE, Double.class);

        AppCache.getInstance().save(KEY_BOOLEAN, true);
        AppCache.getInstance().setOnlyMemoryCache().get(KEY_BOOLEAN, Boolean.class);
        AppCache.getInstance().setOnlyDiskCache().get(KEY_BOOLEAN, Boolean.class);
    }
}
