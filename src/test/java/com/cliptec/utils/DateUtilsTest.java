package com.cliptec.utils;

import org.junit.Test;

import com.cliptec.utils.DateUtils;

/**
 * Created with IntelliJ IDEA.
 * User: slama
 * Date: 12/5/13
 * Time: 4:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class DateUtilsTest {

    @Test
    public void testCalcAgeFromDOB(){
        System.out.println(DateUtils.calcAgeFromDOB("1989-04-27"));
    }

}
