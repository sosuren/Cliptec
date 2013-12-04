package com.cliptec.utils;

import com.cliptec.layout.api.LayoutParser;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: slama
 * Date: 12/4/13
 * Time: 2:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileUtils {

    public static void readFile(String filePath, LayoutParser layoutParser){
        try{
            FileReader fileReader = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fileReader);

            String line;

            while((line = reader.readLine()) != null){
                layoutParser.parseLine(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public interface Parser{
        public void parserLine(String line);
    }

}
