package com.imageco.special.util;

import cn.xddai.chardet.CharsetDetector;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by IntelliJ IDEA.
 * User: OYQX
 * Date: 11-10-27
 * Time: 下午1:45
 */
public class CodeOperation {

    public static String all2utf8(String str) {
        CharsetDetector charDect = new CharsetDetector();
        String returnStr = "";
        byte texts[] = str.getBytes();
        try {
            String[] probableSet = charDect.detectChineseCharset(new ByteArrayInputStream(texts));
//            for (String charset : probableSet) {
//                System.out.println(charset);
//
//            }

            for(int i=0;i<probableSet.length;i++){
                if(probableSet[i].contains("GB2312")){
//                   returnStr=gb2312toUtf8(probableSet[i]);
                   str=gb2312toUtf8(str);
                }else if(probableSet[i].contains("Big5")) {
//                    returnStr=big5toUtf8(probableSet[i]);
                    str=big5toUtf8(str);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            System.out.println("CodeOperation all2utf8 wrong");
        }
        return str;
    }

    private static String big5toUtf8(String s) {
        //To change body of created methods use File | Settings | File Templates.
        String str = null;
        try {
//            new   String(str1.getBytes(encoding1),encoding2);
            str= new String(s.getBytes(),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return str;
    }

    private static String gb2312toUtf8(String s) {
           //To change body of created methods use File | Settings | File Templates.
        String str = null;
        try {
            str= new String(s.getBytes(),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return str;
    }
}
