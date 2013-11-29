package com.cliptec.utils;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import com.cliptec.utils.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * User: slama
 * Date: 11/26/13
 * Time: 5:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class StringUtilsTest {

    @Test
    public void testTrimWith(){
        System.out.println(StringUtils.trimWith("\"hello\"", "\""));
    }
}
