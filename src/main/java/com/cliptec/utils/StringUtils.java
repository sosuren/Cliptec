package com.cliptec.utils;

/**
 * Created with IntelliJ IDEA.
 * User: slama
 * Date: 12/5/13
 * Time: 1:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class StringUtils {

    public static String trimWith(String target, String trimChar){
        while(target.startsWith(trimChar) || target.endsWith(trimChar)){
            if(target.startsWith(trimChar))
                target = target.substring(1, target.length());
            if(target.endsWith(trimChar))
                target = target.substring(0, target.length() -1 );
        }
        return target;
    }
}
