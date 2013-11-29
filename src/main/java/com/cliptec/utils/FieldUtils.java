package com.cliptec.utils;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.SortedSet;
import java.util.TreeSet;

import cascading.tuple.Fields;

import com.cliptec.constants.Layout;

/**
 * Created with IntelliJ IDEA.
 * User: slama
 * Date: 11/29/13
 * Time: 1:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class FieldUtils {



    public static Fields getFieldsFromLayout(String filePaths, Layout layout){

        String[] filePathList = filePaths.split(",");

        Fields fields = null;

        Set<String> fieldList = new HashSet<String>();

        for(String filePath: filePathList){
            //System.out.println(filePath);

            filePath.trim();
            if(fields == null){
                fieldList.addAll(FileUtils.parseLayoutFileInClassPath(filePath));
            }
            else{
                fieldList.addAll((FileUtils.parseLayoutFileInClassPath(filePath)));
            }
        }

        SortedSet<String> fieldSet = new TreeSet<String>();

        for(String layoutLine: fieldList){
            if(fields == null){

                fieldSet.add(StringUtils.trimWith(layoutLine.split(";")[layout.ordinal()], "\""));
            }
            else{
                fieldSet.add(StringUtils.trimWith(layoutLine.split(";")[layout.ordinal()], "\""));
            }
        }

        String[] fieldArray = null;
        fieldSet.toArray(fieldArray);

        fields = new Fields(fieldSet.toString());

        return fields;
    }
}
