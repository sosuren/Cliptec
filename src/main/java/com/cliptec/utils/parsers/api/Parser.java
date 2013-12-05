package com.cliptec.utils.parsers.api;

import com.cliptec.layout.LayoutEntryList;

/**
 * Created with IntelliJ IDEA.
 * User: slama
 * Date: 12/4/13
 * Time: 2:38 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Parser {

    LayoutEntryList entryList = new LayoutEntryList();

    public void parseLine(String line);

    public LayoutEntryList getEntryList();
}
