package com.cliptec.constants;

import org.junit.Test;

import com.cliptec.constants.*;

/**
 * Created with IntelliJ IDEA.
 * User: slama
 * Date: 11/29/13
 * Time: 5:45 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConstantsTest {

    @Test
    public void testLayout(){
        Layout layout = Layout.STOREDNAME;
        System.out.println(layout.ordinal());
    }

}
