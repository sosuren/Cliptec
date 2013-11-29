package com.cliptec.utils;

import cascading.tuple.Fields;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
/**
 * Created with IntelliJ IDEA.
 * User: slama
 * Date: 11/25/13
 * Time: 6:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileUtils {

    public static List<String> parseLayoutFileInClassPath(String filePath){

        List<String> fieldList = new ArrayList<String>();

        try{

            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = null;

            while((line = bufferedReader.readLine()) != null){
                if(line.startsWith("#"))
                    continue;

                fieldList.add(line);

            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return fieldList;
    }

}
