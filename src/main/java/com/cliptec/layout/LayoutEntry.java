package com.cliptec.layout;

/**
 * Created with IntelliJ IDEA.
 * User: slama
 * Date: 12/4/13
 * Time: 5:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class LayoutEntry {

    public int position;

    public String scrubName;
    public String displayName;
    public String storedName;
    public String type;
    public String format;
    public String parent;

    @Override
    public String toString(){
        return position + ";" + scrubName + ";" +displayName + ";" + storedName + ";" + type + ";" + format + ";" + parent + "\n :(";
    }

}
