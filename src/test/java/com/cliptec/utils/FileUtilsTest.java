package com.cliptec.utils;

import org.junit.Test;

import com.cliptec.utils.FileUtils;

/**
 * Created with IntelliJ IDEA.
 * User: slama
 * Date: 11/26/13
 * Time: 9:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class FileUtilsTest {

    @Test
    public void testParseLayoutFileInClassPath(){
        String inputFilePath = "/home/slama/workspace/Demos/CascadingDemo/src/test/resources/layouts/eligibilityLayout.csv";

        FileUtils.parseLayoutFileInClassPath(inputFilePath);
    }
}
