package com.cliptec.utils;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.cliptec.utils.parsers.api.Parser;

/**
 * Created with IntelliJ IDEA.
 * User: slama
 * Date: 12/4/13
 * Time: 2:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class FileUtils {

    public static void readFile(String filePath, Parser parser){
        try{
            FileReader fileReader = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fileReader);

            String line;

            while((line = reader.readLine()) != null){
                parser.parseLine(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

}
