package com.cliptec.layout;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.cliptec.exceptions.FieldException;

/**
 * Created with IntelliJ IDEA.
 * User: slama
 * Date: 12/5/13
 * Time: 11:31 AM
 * To change this template use File | Settings | File Templates.
 */
public class LayoutEntryList {
    List<LayoutEntry> entryList;

    public LayoutEntryList(){
        entryList = new ArrayList<LayoutEntry>();
    }

    public void addEntry(LayoutEntry entry) throws FieldException{

        for(LayoutEntry fromList : entryList){
            if(fromList.scrubName.equalsIgnoreCase(entry.scrubName) ||
                    fromList.displayName.equalsIgnoreCase(entry.displayName) ||
                    fromList.storedName.equalsIgnoreCase(entry.storedName)){
                 //FieldException e = new FieldException("Duplicate field exists with: " + entry);
                 throw new FieldException("Duplicate field exists with: " + entry);

            }
        }
        entryList.add(entry);
    }

    public Iterator<LayoutEntry> iterator(){

        return entryList.iterator();
    }


    public Object[] toArray(){
        return entryList.toArray();
    }

    public LayoutEntry get(int idx){

        return entryList.get(idx);
    }

    public List<LayoutEntry> getList(){
        return entryList;
    }
}
