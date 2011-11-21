package com.imageco.special.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;
import com.imageco.special.itake.gloable.Constant;
import com.imageco.special.util.net.Connection;
import com.imageco.special.util.other.ApplicationContext;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: OYQX
 * Date: 11-10-22
 * Time: 下午7:08
 */
public class DataOperation {

    /**
     * Field mContext
     */
    private static Context mContext = ApplicationContext.getInstance();
/**********************************************************************************/
/********************************READ**********************************************/
/********************************READ**********************************************/
/**********************************************************************************/
    /**
     * readHttp
     *
     * @param urlString 网址
     * @return 数据显示
     */
    public static String readHttp(String urlString) {
        Connection conn = new Connection(urlString);
        String j = null;
        if (conn.connect())
            j = conn.getData();
        conn.close();
        return j;

    }

    /**
     * Method readFile ...不存在则创建
     *
     * @param fileName 需要获取的文件
     * @return ArrayList<JSONObject>   1 {content:"decode content",date:"2011/01/01"}
     */
    public static ArrayList<JSONObject> readFile(String fileName) {
        Properties mProperties = new Properties();
        ArrayList<JSONObject> mArrayList = new ArrayList<JSONObject>();
//        System.out.println("*****method******" + new Exception().getStackTrace()[0].getMethodName() + "**************");
        try {
            FileInputStream stream;
            stream = mContext.openFileInput(fileName);
            mProperties.load(stream);
            int mSize = mProperties.size();
//            System.out.println(mSize);
            for (int i = 0; i < mSize; i++) {
                String mJsonString = (String) mProperties.get(i + "");
//                System.out.println(mJsonString + "**************");

                try {
                    mArrayList.add(new JSONObject(mJsonString));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("file not found ,file will be created");
            try {
                FileOutputStream out = mContext.openFileOutput(fileName, Activity.MODE_APPEND);
                System.out.println("文件" + fileName + "创建成功");
                out.close();
            } catch (IOException e1) {
                System.out.println("创建文件失败");
            }
        }
        return mArrayList;
    }

    /**
     * Method db2Show ...
     */
    public static void db2Show() {

    }

    /**
     * Method perference2Show ...
     */
    public static void perference2Show() {

    }

/**********************************************************************************/
/********************************Write*********************************************/
/********************************Write*********************************************/
/**********************************************************************************/

    /**
     * ********to file http方式下载文件到本地
     *
     * @param url 网址
     * @return 是否成功***************
     */
    public static Boolean http2File(String url) {


        return false;
    }

    /**
     * Method writeString2File:保存到的内部存储的文件是专用于您的应用程序和其他应用程序不能访问它们
     * （也可以用户）。当用户卸载您的应用程序时，将删除这些文件。
     *
     * @param fileName 文件名
     * @param content  需要存入的内容(String)
     * @param mode
     * @return Boolean 成功TRUE,失败FALSE
     */
    public static Boolean writeString2File(String fileName, String content, int mode) {
        Boolean ok;
        try {
            FileOutputStream out = mContext.openFileOutput(fileName, mode);
            Properties properties = new Properties();
            String filekey = new Date().getTime() + "";
            properties.put(filekey + "", content);
//            System.out.println("写入文件操作" + filekey + "\n" + "******content" + content);
            properties.store(out, "");
            out.close();
            ok = true;
        } catch (IOException e) {
            ok = false;
            e.printStackTrace();
        }
        return ok;
    }

    /**
     * Method addArrayMap2File ...
     *
     * @param fileName of type String 写入的文件名
     * @param array    of type ArrayList<JSONObject> 数据array COLLECTION
     * @param mode     of type int  模式Activity.MODE
     * @return Boolean
     */
    public static Boolean addArrayMap2File(String fileName, ArrayList<JSONObject> array, int mode) {

        Boolean ok;
//        System.out.println("*****method******" + new Exception().getStackTrace()[0].getMethodName() + "**************");
        ArrayList<JSONObject> tempArray;
        tempArray = readFile(fileName);//8
        int oldSize = array.size();//0
        for (int k = 0; k < oldSize; k++) {
            tempArray.add(array.get(k));//8+10
//            System.out.println(array.get(k) + "");
        }

        try {

            FileOutputStream out = mContext.openFileOutput(fileName, mode);

            Properties properties = new Properties();

            int size = tempArray.size();

            for (int i = 0; i < size; i++) {

                properties.put(i + "", tempArray.get(i) + "");

            }


            properties.store(out, "");

            out.close();

            ok = true;
        } catch (IOException e) {

            ok = false;

            e.printStackTrace();

        }
        return ok;
    }

    /**
     * Method refreshFile ...
     *
     * @param fileName of type String
     * @param array    of type ArrayList<JSONObject>
     * @return Boolean
     */
    public static Boolean refreshFile(String fileName, ArrayList<JSONObject> array) {

        Boolean ok;
//        System.out.println("*****method******" + new Exception().getStackTrace()[0].getMethodName() + "**************");
        try {

            FileOutputStream out = mContext.openFileOutput(fileName, Context.MODE_PRIVATE);

            Properties properties = new Properties();

            int size = array.size();

            for (int i = 0; i < size; i++) {

                properties.put(i + "", array.get(i) + "");

            }


            properties.store(out, "");

            out.close();

            ok = true;
        } catch (IOException e) {

            ok = false;

            e.printStackTrace();

        }
        return ok;
    }


    /**
     * writePerference: Store private primitive data in key-value pairs.
     *
     * @param perferencesName 需要保存的配置文件名称
     * @param mode            Activity.MODE_PRIVATE等
     * @param map             map<key,value>需要存入的字符串,以及对应的Boolean值</key,value>
     */
    public static void writePerference(String perferencesName, int mode, HashMap<String, Object> map) {

        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(perferencesName, mode);

        SharedPreferences.Editor editor = mSharedPreferences.edit();

        String type;

        for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); ) {
            Properties.Entry next = (Map.Entry) iterator.next();
            String key = (String) next.getKey();
            Object value = next.getValue();
            type = value.getClass().getSimpleName();
            if (type.equals("String")) {
                editor.putString(key, (String) value);
            } else if (type.equals("Boolean")) {
                editor.putBoolean(key, (Boolean) value);
            } else if (type.equals("Float")) {
                editor.putFloat(key, (Float) value);
            } else if (type.equals("Integer")) {
                editor.putInt(key, (Integer) value);
            } else if (type.equals("Long")) {
                editor.putLong(key, (Long) value);
            }

        }
        editor.commit();

    }

    /**
     * Method writeExternal ...
     */
    public static void writeExternal() {

    }

    /**
     * Method isExternalAvailable returns the externalAvailable of this DataOperation object.
     *
     * @param what 0:返回是否可用,1返回是否可写
     * @return the externalAvailable (type boolean) of this DataOperation object.
     */
    public static boolean isExternalAvailable(int what) {
        boolean mExternalStorageAvailable = false;
        boolean mExternalStorageWriteable = false;
        boolean result = false;
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {    // We can read and write the media
            mExternalStorageAvailable = mExternalStorageWriteable = true;
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {    // We can only read the media
            mExternalStorageAvailable = true;
            mExternalStorageWriteable = false;
        } else {    // Something else is wrong. It may be one of many other states, but all we need
            //  to know is we can neither read nor write
            mExternalStorageAvailable = mExternalStorageWriteable = false;
        }
        if (what == 0) {
            result = mExternalStorageAvailable;
        } else if (what == 1) {
            result = mExternalStorageWriteable;
        }
        return result;
    }

    /**
     * Method deleteFileItemInPos ...
     *
     * @param fileName of type String  文件名
     * @param position of type int  需要删除行的位置
     */
    //删除file存储键值对的某一条数据
    public static void deleteFileItemInPos(String fileName, int position) {
//        System.out.println("*****method******" + new Exception().getStackTrace()[0].getMethodName() + "**************");
//        System.out.println("*****method******" + new Exception().getStackTrace()[0].getMethodName() + "**************");
//        System.out.println("*****method******" + new Exception().getStackTrace()[0].getMethodName() + "**************");
//        System.out.println("*****method******" + new Exception().getStackTrace()[0].getMethodName() + "**************");
//        System.out.println("*****method******" + new Exception().getStackTrace()[0].getMethodName() + "**************");
//        System.out.println("*****method******" + new Exception().getStackTrace()[0].getMethodName() + "**************");
        ArrayList<JSONObject> mArrayList;
        mArrayList = readFile(Constant.FILENAME);
//        System.out.println("文件的长度是================="+mArrayList.size());
//        System.out.println("需要移除的position是========="+position);
        mArrayList.remove(position);
//        System.out.println("==============================================");
        refreshFile(Constant.FILENAME, mArrayList);


    }
}
