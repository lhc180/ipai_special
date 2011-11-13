package com.imageco.test;

/**
 * Created by IntelliJ IDEA.
 * User: OYQX
 * Date: 11-10-31
 * Time: 下午12:57
 */
public class TestString {
    public static void main(String[] args) {
        String testStr = "http://222.44.51.34/aipai/upload/1319592025.apk";
        System.out.println(testStr.substring(testStr.lastIndexOf('/') + 1));
    }
}
