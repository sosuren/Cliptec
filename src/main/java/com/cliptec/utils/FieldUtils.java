package com.cliptec.utils;

import cascading.tuple.Fields;

import com.cliptec.constants.DefaultConstants;
import com.cliptec.layout.LayoutEntry;
import com.cliptec.layout.LayoutEntryList;
import com.cliptec.utils.parsers.LayoutParser;
import com.cliptec.utils.parsers.api.Parser;

/**
 * Created with IntelliJ IDEA.
 * User: slama
 * Date: 12/5/13
 * Time: 12:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class FieldUtils {

    private Parser parser;

    public FieldUtils(){

    }

    public Fields getFieldsFromLayoutFiles(String filePaths){
        Fields fields = null;

        parser = new LayoutParser();

        for(String filePath: filePaths.split(DefaultConstants.LAYOUT_PATH_DELIMETER)){
            FileUtils.readFile(filePath, parser);
        }

        LayoutEntryList entryList = parser.getEntryList();

        for(LayoutEntry entry: entryList.getList()){
            if(fields == null){
                fields = new Fields(entry.scrubName);
            }
            else{
                fields = fields.append(new Fields(entry.scrubName));
            }
        }

        //System.out.println(fields.toString());

        return fields;
    }

}
