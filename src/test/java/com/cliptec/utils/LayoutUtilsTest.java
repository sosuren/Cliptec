package com.cliptec.utils;

import com.cliptec.constants.DefaultConstants;
import com.cliptec.utils.LayoutUtils;

import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: slama
 * Date: 12/5/13
 * Time: 1:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class LayoutUtilsTest {

    @Test
    public void testReadEntry(){

        String line = "1;\"dw_record_id\";\"rowID\";\"rowID\";\"string\";;mee";

        LayoutUtils utils = new LayoutUtils();

        System.out.println(utils.readEntry(line));
    }
}
