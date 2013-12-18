package com.cliptec.utils;

import com.cliptec.constants.LayoutConstants;
import com.cliptec.constants.LayoutEnum;
import com.cliptec.constants.DefaultConstants;
import com.cliptec.layout.LayoutEntry;

/**
 * Created with IntelliJ IDEA.
 * User: slama
 * Date: 12/4/13
 * Time: 4:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class LayoutUtils {

    private String delim;

    public LayoutUtils(){
        this.delim = LayoutConstants.LAYOUT_ENTRY_DELIMETER;
    }

    public LayoutUtils(String delim){
        this.delim = delim;
    }

    public String readScrubName(String line){
        return line.split(this.delim)[LayoutEnum.SCCRUBNAME.ordinal()];
    }

    public String readDisplayName(String line){
        return line.split(this.delim)[LayoutEnum.DISPLAYNAME.ordinal()];
    }

    public String readStoredName(String line){
        return line.split(this.delim)[LayoutEnum.STOREDNAME.ordinal()];
    }

    public String read(String line, LayoutEnum entry){
        return line.split(this.delim)[entry.ordinal()];
    }

    public LayoutEntry readEntry(String line){

        LayoutEntry entry = new LayoutEntry();
        String[] entryArray = line.split(LayoutConstants.LAYOUT_ENTRY_DELIMETER, line.length());

        entry.position = Integer.parseInt(StringUtils.trimWith(entryArray[LayoutEnum.POSITION.ordinal()].trim(), "\""));
        entry.scrubName = StringUtils.trimWith(entryArray[LayoutEnum.SCCRUBNAME.ordinal()].trim(), "\"");
        entry.displayName = StringUtils.trimWith(entryArray[LayoutEnum.DISPLAYNAME.ordinal()].trim(), "\"");
        entry.storedName = StringUtils.trimWith(entryArray[LayoutEnum.STOREDNAME.ordinal()].trim(), "\"");
        entry.type = StringUtils.trimWith(entryArray[LayoutEnum.TYPE.ordinal()].trim(), "\"");
        entry.format = StringUtils.trimWith(entryArray[LayoutEnum.FORMAT.ordinal()].trim(), "\"");
        entry.parent = StringUtils.trimWith(entryArray[LayoutEnum.PARENT.ordinal()].trim(), "\"");

        return  entry;
    }
}
