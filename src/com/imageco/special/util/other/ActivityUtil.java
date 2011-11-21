package com.imageco.special.util.other;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.DisplayMetrics;

/**
 * Created by IntelliJ IDEA.
 * User: OYQX
 * Date: 11-10-20
 * Time: 下午5:38
 */
public class ActivityUtil {
    /**
     * Field ALARM_RECORED
     */
    public static int ALARM_RECORED = -1;

    /**
     * Field CURRENT_USER_LEVEL
     */
    public static int CURRENT_USER_LEVEL = 0;

    /**
     * Method buildMainDialog ...
     *
     * @param paramContext of type Context
     * @return AlertDialog
     */
    public static AlertDialog buildMainDialog(Context paramContext) {
        return null;
    }

    /**
     * Method getDisplayInfo ...
     *
     * @param paramActivity of type Activity
     * @return DisplayMetrics
     */
    public static DisplayMetrics getDisplayInfo(Activity paramActivity) {
        return paramActivity.getResources().getDisplayMetrics();
    }

    /**
     * Method getPreference ...
     *
     * @param paramContext of type Context
     * @param paramString1 of type String
     * @param paramString2 of type String
     * @return String
     */
    public static String getPreference(Context paramContext,
                                       String paramString1, String paramString2) {
        return paramContext.getSharedPreferences("com.cssweb.lcdt.client", 0)
                .getString(paramString1, paramString2);
    }

    /**
     * 获取服务密码账号
     *
     * @param paramContext
     * @param paramString1
     * @param paramString2
     * @return
     */
    public static String getCustNoPreference(Context paramContext,
                                             String paramString1, String paramString2) {
        return paramContext.getSharedPreferences("com.cssweb.lcdt.clientcustno", 0)
                .getString(paramString1, paramString2);
    }

    /**
     * 服务密码保存账号
     *
     * @param paramContext
     * @param paramString1
     * @param paramString2
     */
    public static void saveCustNoPreference(Context paramContext,
                                            String paramString1, String paramString2) {
        SharedPreferences.Editor localEditor = paramContext
                .getSharedPreferences("com.cssweb.lcdt.clientcustno", 0).edit();
        localEditor.putString(paramString1, paramString2);
        localEditor.commit();
    }

    /**
     * Method savePreference ...
     *
     * @param paramContext of type Context
     * @param paramString1 of type String
     * @param paramString2 of type String
     */
    public static void savePreference(Context paramContext,
                                      String paramString1, String paramString2) {
        SharedPreferences.Editor localEditor = paramContext
                .getSharedPreferences("com.cssweb.lcdt.client", 0).edit();
        localEditor.putString(paramString1, paramString2);
        localEditor.commit();
    }

    /**
     * Method getPreference ...
     *
     * @param paramContext of type Context
     * @param paramString1 of type String
     * @param paramString2 of type int
     * @return int
     */
    public static int getPreference(Context paramContext, String paramString1,
                                    int paramString2) {
        return paramContext.getSharedPreferences("com.cssweb.lcdt.client", 0)
                .getInt(paramString1, paramString2);
    }

    /**
     * Method savePreference ...
     *
     * @param paramContext of type Context
     * @param paramString1 of type String
     * @param paramString2 of type int
     */
    public static void savePreference(Context paramContext,
                                      String paramString1, int paramString2) {
        SharedPreferences.Editor localEditor = paramContext
                .getSharedPreferences("com.cssweb.lcdt.client", 0).edit();
        localEditor.putInt(paramString1, paramString2);
        localEditor.commit();
    }

    /**
     * Method clearPreference ...
     *
     * @param paramContext of type Context
     */
    public static void clearPreference(Context paramContext) {
        SharedPreferences.Editor localEditor = paramContext
                .getSharedPreferences("com.cssweb.lcdt.client", 0).edit();
        localEditor.clear();
        localEditor.commit();
    }

}
