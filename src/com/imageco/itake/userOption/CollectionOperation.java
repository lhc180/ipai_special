package com.imageco.itake.userOption;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.imageco.itake.gloable.Constant;
import com.imageco.util.decoding.Base64Encoder;
import com.imageco.util.net.Conn;
import com.imageco.util.other.ApplicationContext;
import org.json.JSONObject;

import java.util.Locale;

/**
 * 收集相关信息
 * Created by IntelliJ IDEA.
 * User: OYQX
 * Date: 11-10-25
 * Time: 上午10:06
 */
@SuppressWarnings({"unchecked"})
public class CollectionOperation {
    /* 屏幕的宽高 */
    public static int SCREENW;
    public static int SCREENH;
    private static Context mContext;
    private static String versionmsg = null;
    public static JSONObject jsonObject;
    public static JSONObject adsJson;
    public static String fileName;

    static {
        mContext = ApplicationContext.getInstance();
    }


    /**
     * Method getDisplay returns the display of this CollectionOperation object.
     *
     * @return the display (type String) of this CollectionOperation object.
     */
    public static String getDisplay() {
/* 定义DisplayMetrics对象 */
        DisplayMetrics dm = mContext.getApplicationContext().getResources().getDisplayMetrics();

        /* 窗口的宽度 */
        int screenWidth = dm.widthPixels;

        /* 窗口的高度 */
        int screenHeight = dm.heightPixels;
        String mDisplay = "";
        if (screenWidth == 320) {//320*480
            mDisplay = "1";
        } else if (screenWidth == 480) {//480*800
            mDisplay = "2";
        } else {
            mDisplay = "4";//机型不匹配
        }
//        System.out.println("======================"+mDisplay);
        try {
            mDisplay = Base64Encoder.encode(mDisplay, "GBK");
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            System.out.println("编码错误");
        }
        return mDisplay;

    }


    /**
     * Method getSDKVersionNumber returns the SDKVersionNumber of this CollectionOperation object.
     *
     * @return the SDKVersionNumber (type int) of this CollectionOperation object.
     */
    public static String getSDKVersionNumber() {
        String sdkVersion;

        sdkVersion = android.os.Build.VERSION.SDK;

        try {
            sdkVersion = Base64Encoder.encode(sdkVersion, "GBK");
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            System.out.println("编码错误");
        }
        return sdkVersion;
    }

    public static String getOs() {
//        return android.os.Build.VERSION.RELEASE;

        String returnStr = "android";

//        returnStr = android.os.Build.VERSION.SDK;

        try {
            returnStr = Base64Encoder.encode(returnStr, "GBK");
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            System.out.println("编码错误");
        }
        return returnStr;
    }

    /**
     * Method getVersionRelease returns the versionRelease of this CollectionOperation object.
     *
     * @return the versionRelease (type String) of this CollectionOperation object.
     */

    public static String getVersionRelease() {
//        return android.os.Build.VERSION.RELEASE;

        String returnStr = android.os.Build.VERSION.RELEASE;

        returnStr = android.os.Build.VERSION.SDK;

        try {
            returnStr = Base64Encoder.encode(returnStr, "GBK");
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            System.out.println("编码错误");
        }
        return returnStr;
    }

    /**
     * Method getSoftVersionName ...
     *
     * @param m_context of type Context
     * @return String
     */
    public static String getSoftVersionName(Context m_context) {
        PackageManager manager = m_context.getPackageManager();

        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(m_context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            System.out.println("包没找到");
        }

        String returnStr;

        assert info != null;
        returnStr = info.versionName;
        ;

        try {
            returnStr = Base64Encoder.encode(returnStr, "GBK");
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            System.out.println("编码错误");
        }
        return returnStr;


    }

    /**
     * Method getNetType returns the netType of this CollectionOperation object.
     *
     * @return the netType (type String) of this CollectionOperation object.
     */
    public static String getNetType() {

        String nettype = "";
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);// 获取系统的连接服务
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();// 获取网络的连接情况
        if (activeNetInfo != null) {
            if (activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                nettype = "WI-FI";
            } else if (activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                nettype = "EDGE/3G";
            }
        } else {
            nettype = "没网络";
        }


        String returnStr;

        returnStr = nettype;

        try {
            returnStr = Base64Encoder.encode(returnStr, "GBK");
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            System.out.println("编码错误");
        }
        return returnStr;

    }

    /**
     * Method getISP returns the ISP of this CollectionOperation object.
     *
     * @return the ISP (type String) of this CollectionOperation object.
     */
    public static String getISP() {

        TelephonyManager telmanager =
                (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);

        String isp = "";
        String opera = telmanager.getSimOperator();
        if (opera != null) {// 网络运营商
            if (opera.equals("46000") || opera.equals("46002")) {
                isp = "中国移动";
            } else if (opera.equals("46001")) {
                isp = "中国联通";
            } else if (opera.equals("46003")) {
                isp = "中国电信";
            }
        }
//        return isp;


        String returnStr;

        returnStr = isp;

        try {
            returnStr = Base64Encoder.encode(returnStr, "GBK");
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            System.out.println("编码错误");
        }
        return returnStr;
    }


    /**
     * Method getOSChar returns the OSChar of this CollectionOperation object.
     *
     * @return the OSChar (type String) of this CollectionOperation object.
     */
    public static String getOSChar() {//中文
//        return Locale.getDefault().getDisplayName();
        String returnStr;

        returnStr = Locale.getDefault().getDisplayName();
        ;

        try {
            returnStr = Base64Encoder.encode(returnStr, "GBK");
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            System.out.println("编码错误");
        }
        return returnStr;


    }

    /**
     * Method getIMEI returns the IMEI of this CollectionOperation object.
     *
     * @return the IMEI (type String) of this CollectionOperation object.
     */
    public static String getIMEI() {//112312413423
        TelephonyManager telmanager =
                (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
//        return telmanager.getDeviceId();

        String returnStr;

        returnStr = telmanager.getDeviceId();
        ;

        try {
            returnStr = Base64Encoder.encode(returnStr, "GBK");
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            System.out.println("编码错误");
        }
        return returnStr;


    }

    /**
     * Method getChannel returns the channel of this CollectionOperation object.
     *
     * @return the channel (type String) of this CollectionOperation object.
     */
    public static String getChannel() {//10

//        return "3";
        String returnStr;

//        returnStr = "3";
        returnStr = Constant.CHANNEL;
//        returnStr = "2";

        try {
            returnStr = Base64Encoder.encode(returnStr, "GBK");
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            System.out.println("编码错误");
        }
        return returnStr;
    }

    /**
     * Method getMobileType returns the mobileType of this CollectionOperation object.
     *
     * @return the mobileType (type String) of this CollectionOperation object.
     */
    public static String getMobileType() {//desire HD
//        return android.os.Build.MODEL;
        String returnStr;

        returnStr = android.os.Build.MODEL;

        try {
            returnStr = Base64Encoder.encode(returnStr, "GBK");
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            System.out.println("编码错误");
        }
        return returnStr;
    }

    /**
     * Method getMessage returns the message of this CollectionOperation object.
     *
     * @return the message (type String) of this CollectionOperation object.
     */
    public static String getMessage() {//
        return null;

    }

    /**
     * Method getContact returns the contact of this CollectionOperation object.
     *
     * @return the contact (type String) of this CollectionOperation object.
     */
    public static String getContact() {
        return null;
    }

    /**
     * Method getVersionName returns the versionName of this CollectionOperation object.
     *
     * @return the versionName (type String) of this CollectionOperation object.
     */
    public static String getVersionName() {//1.0.0
        PackageManager manager = mContext.getPackageManager();
        String returnStr = "";
        try {
            PackageInfo info = manager.getPackageInfo(mContext.getPackageName(), 0);
//            return info.versionName;
            returnStr = info.versionName;
//            System.out.println(returnStr);
            try {
                returnStr = Base64Encoder.encode(returnStr, "GBK");
            } catch (PackageManager.NameNotFoundException e) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            System.out.println("编码错误");
        }
        return returnStr;
    }

    /**
     * Method getVersionCode returns the versionCode of this CollectionOperation object.
     *
     * @return the versionCode (type int) of this CollectionOperation object.
     */
    public static String getVersionCode() {//1
        PackageManager manager = mContext.getPackageManager();

        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(mContext.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            System.out.println("包未找到");
        }
//            return info.versionCode;


        String returnStr;

        assert info != null;
        returnStr = info.versionCode + "";

        try {
            returnStr = Base64Encoder.encode(returnStr, "GBK");
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            System.out.println("编码错误");
        }
        return returnStr;

    }


    /**
     * Method sendUserInfo ...1行为统计
     * 发送数据
     * getData.php?mobile_type=moto525&mobile_number=13815252565&from_channel=10&imei=232323
     * 返回格式
     * 1成功
     * {"result":"0000","msg":"发送成功"}
     * 2失败
     * {"result":"8888","msg":"参数有误"}{"result":"9999","msg":"插入数据异
     * 常"}
     * 如果是8888，9999，则再请求发送一次
     */
    public static void sendUserInfo() {
//        String pathStr = "mobile_type=" + getMobileType() + "&mobile_number=" + "&from_channel=" + "10" + "&imei=" + getIMEI();
//
//        Conn.execute(Constant.URL_TRACK_BASE + Constant.URL_TRACK_ACTION + pathStr);
        new AsyncTask<Void, Void, Boolean>() {

            /**
             * Method doInBackground ...
             *
             * @param params of type Void...
             * @return Boolean
             */
            @Override
            protected Boolean doInBackground(Void... params) {
                try {
                    String pathStr = "mobile_type=" + getMobileType() + "&mobile_number=" + "" + "&imei=" + getIMEI() + "&from_channel=" + getChannel();
                    Conn.execute(Constant.URL_TRACK_BASE + Constant.URL_TRACK_ACTION + pathStr);
                } catch (Exception e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    System.out.println("加密错误");
                }


                return null;  //TODO
            }
        }.execute();

    }

}
