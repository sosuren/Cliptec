package com.cliptec.utils;

import com.cliptec.constants.LayoutEnum;
import com.cliptec.constants.DefaultConstants;

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
        this.delim = DefaultConstants.LAYOUT_DEFAULT_DELIMETER;
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
}
