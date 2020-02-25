package com.xnk.service.provider.utils;

import java.io.UnsupportedEncodingException;

public class UrlEnDeCodeUtils {
	
	public static String getURLEncoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
 
    public static String URLDecoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    
    public static void main(String[] args) {
		System.out.println(URLDecoderString("%E5%A5%BD%E7%9A%84"));;
	}
}
