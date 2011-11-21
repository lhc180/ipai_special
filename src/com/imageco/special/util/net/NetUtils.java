package com.imageco.special.util.net;

import android.content.Context;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetUtils {

    //
    public static byte[] readStream(InputStream inStream) throws Exception {
        byte[] buffer = new byte[1024];
        int len = -1;
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        outStream.close();
        inStream.close();
        return data;
    }

    private static void download(Context context, String urlpath, String name) {
        Log.i("tag", "download" + urlpath);
        try {
            URL url = new URL(urlpath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            if (conn.getResponseCode() == 200) {
                // 获取服务器返回的数据
                byte[] bytedata = NetUtils.readStream(conn.getInputStream());
                // String name = urlpath.substring(urlpath.lastIndexOf("/") +1
                // );
                FileOutputStream fos = context.openFileOutput(name,
                        Context.MODE_PRIVATE);
                fos.write(bytedata);
                fos.close();
            }
        } catch (Exception e) {
        }
    }
}
