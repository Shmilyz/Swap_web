package com.shmilyz.graduation.utils;

import java.util.regex.Pattern;

/**
 * Created by Shmily_Z on 2017/9/10.
 */
public class VerifyUtils {


    public static boolean email(String str){

    String regex="^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$";
    return Pattern.matches(regex, str);
    }

    public static boolean phone(String str){

        String regex="^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
        return Pattern.matches(regex, str);
    }


    public static boolean firstnum(String str){

        String regex="[0-9]*";
        return Pattern.matches(regex, str);
    }


    public static boolean cid(String str){

        String regex="^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$";
        return Pattern.matches(regex, str);
    }


}
