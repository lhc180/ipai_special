package com.imageco.util.net;

/**
 * Created by IntelliJ IDEA.
 * User: OYQX
 * Date: 11-10-30
 * Time: 下午4:38
 */
public class ConnOperation {

    private String urlStr;

    public ConnOperation(String urlStr) {
        this.urlStr = urlStr;
    }


    public boolean directConnect(String url) {
        return false;
    }

    public boolean apacheConnect(String url) {
        return false;
    }

    public boolean androidConnect(String url) {
        return false;
    }
}
