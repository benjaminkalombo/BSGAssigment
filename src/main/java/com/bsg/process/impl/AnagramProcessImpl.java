package com.bsg.process.impl;

import com.bsg.loader.DictionaryLoader;
import com.bsg.process.AnagramProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;

public class AnagramProcessImpl implements AnagramProcess {

    private  DictionaryLoader dictionaryLoader;
    final static Logger logger = LoggerFactory.getLogger(AnagramProcess.class);
  //  @Value("${dictionary.file.path:/dictionary.txt}")
    //private static String dictionaryfile;

    private File file = new File(getClass().getResource("/dictionary.txt").getFile());
    HashMap<String, String> words;

    public AnagramProcessImpl() {

        this.dictionaryLoader = new DictionaryLoader();
        words = dictionaryLoader.loadDictionary(file);

    }

    @Override
    public  String  processAnagram(String s ){
        return processDictionary(s);
    }

    private String processDictionary(String s){

         HashMap<String, String> words = dictionaryLoader.loadDictionary(file);

        int length = s.length();
        int totsize = (int) (Math.pow(2, (double)length)-1);
        HashMap<String, String> verifyHashMapCombination = new HashMap<String, String>(300000);
        String output = "";
        long start = Instant.now().getEpochSecond();

        for (int j=0; j<totsize; j++) {
            String mask = Integer.toBinaryString(j+1);
            String buffer = "";
            int maskLength = mask.length();

            for (int k=maskLength-1; k>=0; k--) {

                if (mask.charAt(k)=='1') {
                    int getChar = length - (maskLength - k);
                    buffer = s.charAt(getChar) + buffer;
                }
            }
            char[] bufferChars = buffer.toCharArray();
            Arrays.sort(bufferChars);
            String sortedBuffer = String.valueOf(bufferChars);
            if (verifyHashMapCombination.get(sortedBuffer) != null) { }
            else {
                verifyHashMapCombination.put(sortedBuffer, "");
                String dictVal = "";
                if ((dictVal = words.get(sortedBuffer)) != null) {

                    String[] dictValSplit = dictVal.split("; ");
                    for (int m=0; m<dictValSplit.length; m++) {
                        output = String.valueOf((dictValSplit[m].length())) + output;
                    }
                }
            }
        }
        long end = Instant.now().getEpochSecond();
        logger.info("Anagram Results completed in: " +  (end  + start) +" ms)");
        return output;
    }
}
