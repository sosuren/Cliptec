package com.cliptec.utils;

import org.junit.Test;

import com.cliptec.utils.FieldUtils;

/**
 * Created with IntelliJ IDEA.
 * User: slama
 * Date: 12/5/13
 * Time: 1:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class FieldUtilsTest {

    @Test
    public void testGetFieldsFromLayoutFiles(){
        String layoutFiles = "/home/slama/workspace/Demos/CascadingDemo/src/test/resources/layouts/eligibilityLayout.csv,/home/slama/workspace/Demos/CascadingDemo/src/test/resources/layouts/medicalLayout.csv";

        FieldUtils utils = new FieldUtils();

        utils.getFieldsFromLayoutFiles(layoutFiles);
    }

}
