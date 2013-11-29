package com.cliptec.utils;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

import cascading.tuple.Fields;

import com.cliptec.utils.FieldUtils;
import com.cliptec.constants.Layout;
/**
 * Created with IntelliJ IDEA.
 * User: slama
 * Date: 11/29/13
 * Time: 1:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class FieldUtilsTest {

    @Test
    public void testGetFieldsFromLayout(){
        String inputFilePath = "/home/slama/workspace/Demos/CascadingDemo/src/test/resources/layouts/eligibilityLayout.csv,/home/slama/workspace/Demos/CascadingDemo/src/test/resources/layouts/medicalLayout.csv";
        //String inputFilePath = "/home/slama/workspace/Demos/CascadingDemo/src/test/resources/layouts/medicalLayout.csv";

        Fields returnedFields = FieldUtils.getFieldsFromLayout(inputFilePath, Layout.SCRUBNAME);

        System.out.println(returnedFields.toString());
    }

    @Test
    public void testHasSet(){
        Set<String> coll = new HashSet<String>();

        coll.add("ell");
        coll.add("Jev");
        coll.add("ell");

        System.out.println(coll.toString());
    }
}
