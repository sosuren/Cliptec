package com.cliptec;

import org.junit.Test;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: slama
 * Date: 12/5/13
 * Time: 10:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestClass {

    @Test
    public void testSetCollection(){
        Set<String> set = new TreeSet<String>();

        set.add("cliptec");
        set.add("acer");
        set.add("dell");
        set.add("hp");
        set.add("sony");
        set.add("apple");

//        if(set.contains("acer")){
//            System.out.println("true");
//        }


        for(Iterator iterator = set.iterator(); iterator.hasNext();){
            System.out.println(iterator.next());
        }
    }
}
