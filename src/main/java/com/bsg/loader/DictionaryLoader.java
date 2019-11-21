package com.bsg.loader;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DictionaryLoader {

   final static Logger logger = LoggerFactory.getLogger(DictionaryLoader.class);

    private HashMap<String, String> words = new HashMap<String, String>(300000);


    public  HashMap<String, String> loadDictionary(File filePath) {
        try {

            try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
                try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream))) {
                    String readLineStr;
                    while ((readLineStr = bufferedReader.readLine()) != null) {
                        //sort the string
                        char[] Chars = readLineStr.toCharArray();
                        Arrays.sort(Chars);
                        String sortedStr = String.valueOf(Chars);

                        if (words.get(sortedStr) == null) {
                            words.put(sortedStr, readLineStr);
                        }

                        else {
                            String previousStr = words.get(sortedStr);
                            words.put(sortedStr, previousStr + "; " + readLineStr);
                        }
                    }

                }
            }

        } catch (IOException e) {
            logger.error("Error in loading dictionary from file, kindly check if file exist.");
            e.printStackTrace();
        }
        return words;

    }
}


