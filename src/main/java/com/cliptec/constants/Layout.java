package com.cliptec.constants;

/**
 * Created with IntelliJ IDEA.
 * User: slama
 * Date: 11/29/13
 * Time: 5:30 PM
 * To change this template use File | Settings | File Templates.
 */
public enum Layout {
    POSITION(0),
    SCRUBNAME(1),
    DISPLAYNAME(2),
    STOREDNAME(3),
    TYPE(4),
    FORMAT(5),
    PARENT(6);

    private int value;

    private Layout(int value){
        this.value = value;
    }
}
