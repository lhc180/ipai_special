package com.imageco.special.util.other;

import android.app.Application;

/**
 * 用于获取程序context等内容 manifest配置Application
 * android:name="com.imageco.special.util.other.ApplicationContext"
 *
 * @author oyqx
 */
public class ApplicationContext extends Application {
    /** Field instance  */
    private static ApplicationContext instance;

    /**
     * @return context
     */
    public static ApplicationContext getInstance() {
        return instance;
    }

    /**
     * Method onCreate ...
     */
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}