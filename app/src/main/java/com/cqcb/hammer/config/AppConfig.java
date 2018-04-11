package com.cqcb.hammer.config;

import android.app.Application;
import android.content.Context;
import android.support.v4.app.FragmentManager;


import com.synews.hammer.base.delegate.AppLifecycles;
import com.synews.hammer.di.module.AppConfigModule;
import com.synews.hammer.integration.ConfigModule;

import java.util.List;

/**
 * @author 陈东  -  2018/4/8 - 下午2:50
 * 全局配置
 */
public class AppConfig implements ConfigModule {

    @Override
    public void applyOptions(Context context, AppConfigModule.Builder builder) {

    }

    @Override
    public void injectAppLifecycle(Context context, List<AppLifecycles> lifecycles) {

        //向Application的生命周期中注入一些自定义逻辑
    }

    @Override
    public void injectActivityLifecycle(Context context, List<Application.ActivityLifecycleCallbacks> lifecycles) {
        //向Activity的生命周期中注入一些自定义逻辑
    }

    @Override
    public void injectFragmentLifecycle(Context context, List<FragmentManager.FragmentLifecycleCallbacks> lifecycles) {

    }

}
