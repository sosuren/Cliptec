package com.cliptec.utils;


/**
 * Created with IntelliJ IDEA.
 * User: slama
 * Date: 11/26/13
 * Time: 5:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class StringUtils {

    public static String trimWith(String line, String delim){
        while(line.startsWith(delim) || line.endsWith(delim)){
            if(line.startsWith(delim)){

                line = line.substring(1);
            }
            else{

                line = line.substring(0, line.length() - 1);
            }

        }
        return line;
    }
}