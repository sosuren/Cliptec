package com.cliptec;

import org.junit.Ignore;

/**
 * Created with IntelliJ IDEA.
 * User: slama
 * Date: 12/5/13
 * Time: 5:01 PM
 * To change this template use File | Settings | File Templates.
 */
@Ignore
public class TestFileLoader {

    public String getFilePath(String fileName){
        return getClass().getClassLoader().getResource(fileName).toString().replace("file:/", "/");
    }
}
