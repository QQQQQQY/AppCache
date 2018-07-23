package com.example.com.cachetest;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * @Author: qq.yang.
 * @Date: 2018/7/23 0023 10:33.
 * @Description: .
 */
public class BaseApplication extends Application {

    private static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        this.instance = this;
        FlowManager.init(new FlowConfig.Builder(this).build());
    }

    public static Application getInstance() {
        return instance;
    }
}
