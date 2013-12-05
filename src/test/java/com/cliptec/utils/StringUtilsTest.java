package com.cliptec.utils;

import com.cliptec.utils.StringUtils;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: slama
 * Date: 12/5/13
 * Time: 1:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class StringUtilsTest {

    @Test
    public void testTrimWith(){
        String target = "\"\"hist\"";
        System.out.println(StringUtils.trimWith(target, "\""));
    }

}
