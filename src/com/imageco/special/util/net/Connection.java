package com.imageco.special.util.net;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Class Connection ...
 *
 * @author Administrator
 *         Created on 11-10-22
 */
public class Connection {
    /**
     * Field DEBUG_TAG
     */
    private final String DEBUG_TAG = "Connection";
    /**
     * Field m_conn
     */
    private HttpURLConnection m_conn = null;
    /**
     * Field req
     */
    private String req = null;
    /**
     * Field res
     */
    private String res = null;
    /**
     * Field j
     */
    private JSONObject j = null;

    /**
     * Constructor Connection creates a new Connection instance.
     *
     * @param req of type String
     */
    public Connection(String req) {
        this.req = req;
    }

    /**
     * Method close ...
     */
    public void close() {
        if (this.m_conn == null)
            return;
        this.m_conn.disconnect();
        this.m_conn = null;
    }

    /**
     * Method connect ...检测网络
     *
     * @return boolean
     */
    public boolean connect() {
        try {
            URL url = new URL(req);
            this.m_conn = (HttpURLConnection) url.openConnection();
            this.m_conn.setConnectTimeout(10000);
            this.m_conn.setReadTimeout(6000);
            this.m_conn.setDoInput(true);
            this.m_conn.setDoOutput(true);
        } catch (MalformedURLException e) {
            Log.e(DEBUG_TAG, e.toString());
            return false;
        } catch (IOException e) {
            Log.e(DEBUG_TAG, e.toString());
            return false;
        }
        return true;
    }



    /**
     * Method execute ...
     *
     * @return JSONObject
     */
    public JSONObject execute() {
        if (req == null || req.length() == 0)
            return null;
        InputStreamReader in = null;
        BufferedReader buffer = null;
        StringBuffer text = new StringBuffer();
        try {
            in = new InputStreamReader(this.m_conn.getInputStream(), "UTF-8");
            buffer = new BufferedReader(in);
            while ((res = buffer.readLine()) != null) {
                //arrayOutputStream.write(res.getBytes());
                text.append(res);
            }
            //byte[] b = arrayOutputStream.toByteArray();
            //String response = new String(b);
            j = new JSONObject(text.toString());
            //arrayOutputStream.close();
            //in.close();
        } catch (IOException e) {
            j = null;
            in = null;
            buffer = null;
            Log.e(DEBUG_TAG, e.toString());
        } catch (JSONException e) {
            j = null;
            in = null;
            buffer = null;
            Log.e(DEBUG_TAG, e.toString());
        } finally {
            res = null;
            text = null;
            try {
                if (in != null) in.close();
            } catch (IOException e) {
            }
            try {
                if (buffer != null) buffer.close();
            } catch (IOException e) {
            }
        }
        return j;
    }

    /**
     * Method getF10Data returns the f10Data of this Connection object.
     *
     * @return the f10Data (type String) of this Connection object.
     */
    public String getF10Data() {
        if (req == null || req.length() == 0)
            return null;
        InputStreamReader in = null;
        BufferedReader buffer = null;
        StringBuffer text = new StringBuffer();
        try {
            in = new InputStreamReader(this.m_conn.getInputStream(), "UTF-8");
            buffer = new BufferedReader(in);
            while ((res = buffer.readLine()) != null) {
                text.append(res + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(DEBUG_TAG, e.toString());
        } finally {
            try {
                if (in != null) in.close();
            } catch (IOException e) {
            }
            try {
                if (buffer != null) buffer.close();
            } catch (IOException e) {
            }
        }
        return text.toString();
    }

    /**
     * Method getData returns the data of this Connection object.
     *
     * @return the data (type String) of this Connection object.
     */
    public String getData() {
        if (req == null || req.length() == 0)
            return null;
        try {
            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
            InputStreamReader in = new InputStreamReader(this.m_conn.getInputStream(), "UTF-8");
            BufferedReader buffer = new BufferedReader(in);
            String res = null;
            while ((res = buffer.readLine()) != null) {
                arrayOutputStream.write(res.getBytes());
            }
            //in.close();
            byte[] b = arrayOutputStream.toByteArray();
            return new String(b);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(DEBUG_TAG, e.toString());
            return null;
        }
    }

    /**
     * Method getUserLevel returns the userLevel of this Connection object.
     *
     * @return the userLevel (type String) of this Connection object.
     */
    public String getUserLevel() {
        InputStreamReader sr;
        try {
            sr = new InputStreamReader(this.m_conn.getInputStream(), "UTF-8");
            StringBuilder builder = new StringBuilder();
            for (int bt = 0; (bt = sr.read()) != -1; ) {
                builder.append((char) bt);
            }
            sr.close();
            Log.i("#######getUserLevel#######", builder.toString() + "<<<<<<<<<<<<");
            return builder.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Method execute ...
     *
     * @param isWrapped of type boolean
     * @return JSONObject
     */
    public JSONObject execute(boolean isWrapped) {
        StringBuffer data = new StringBuffer();
        if (req == null || req.length() == 0)
            return null;
        BufferedReader buffer = null;
        ByteArrayOutputStream arrayOutputStream = null;
        InputStreamReader in = null;
        try {
            in = new InputStreamReader(this.m_conn.getInputStream(), "UTF-8");
            arrayOutputStream = new ByteArrayOutputStream();
            buffer = new BufferedReader(in);
            while ((res = buffer.readLine()) != null) {
                arrayOutputStream.write(res.getBytes());
            }
            in.close();
            byte[] b = arrayOutputStream.toByteArray();
            if (isWrapped) {
                data.append("{\"data\":[");
            }

            if (b != null) {
                data.append(new String(b));
            }

            if (isWrapped) {
                data.append("]}");
            }
            j = new JSONObject(data.toString());
        } catch (IOException e) {
            j = null;
            in = null;
            buffer = null;
            arrayOutputStream = null;
            Log.e(DEBUG_TAG, e.toString());
        } catch (JSONException e) {
            j = null;
            in = null;
            buffer = null;
            arrayOutputStream = null;
            Log.e(DEBUG_TAG, e.toString());
        } finally {
//			try {
//				this.m_conn.getInputStream().close();
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
        }
        return j;
    }

    /**
     * Method getMeta ...
     *
     * @param startStr of type String
     * @param endStr   of type String
     * @param srcStr   of type String
     * @return String
     */
    public static String getMeta(String startStr, String endStr, String srcStr) {
        String returnValue = "";
        try {
            int index = srcStr.indexOf(startStr) + startStr.length();
            srcStr = srcStr.substring(index);
            returnValue = srcStr.substring(0, srcStr.indexOf(endStr));
        } catch (Exception e) {
            e.printStackTrace();

        }
        return returnValue;

    }
}
