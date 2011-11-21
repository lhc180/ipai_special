package com.imageco.special.util.net;

import org.json.JSONObject;

/**
 * Class Conn ...
 *
 * @author Administrator
 *         Created on 11-10-22
 */
public class Conn {
    /**
     * Method getData ...
     *
     * @param req of type String
     * @return String
     */
    public static String getData(String req) {
        Connection conn = new Connection(req);
        boolean conFlag = conn.connect();
        String j = null;
        if (conFlag)
            j = conn.getData();
        conn.close();
        return j;
    }


    /**
     * Method execute ...
     *
     * @param req of type String
     * @return JSONObject
     */
    public static JSONObject execute(String req) {
//        System.out.println("发送请求的地址是==========" + req);
        Connection conn = new Connection(req);
        boolean conFlag = conn.connect();
        JSONObject j = null;
        if (conFlag)
            j = conn.execute();
//        assert j != null;
//        System.out.println(assert j != null);
        if (j != null) {
//            System.out.println("服务器返回是=========>" + j.toString());
        }
        conn.close();
        return j;
    }


}
