package com.cliptec.utils.parsers;

import com.cliptec.constants.DefaultConstants;
import com.cliptec.exceptions.FieldException;
import com.cliptec.layout.LayoutEntryList;
import com.cliptec.utils.parsers.api.Parser;
import com.cliptec.utils.LayoutUtils;

/**
 * Created with IntelliJ IDEA.
 * User: slama
 * Date: 12/4/13
 * Time: 5:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class LayoutParser implements Parser {

    private LayoutUtils utils;

    public LayoutParser(){
        utils = new LayoutUtils();
    }

    @Override
    public void parseLine(String line) {
        if(!line.startsWith(DefaultConstants.DEFAULT_COMMENT_STROKE)){
            try{
                entryList.addEntry(utils.readEntry(line));
            }
            catch(FieldException e){
                e.printStackTrace();
                //exit the system to remove duplicate fields in layout files
                System.exit(1);
            }
        }
    }

    @Override
    public LayoutEntryList getEntryList(){
        return entryList;
    }
}
