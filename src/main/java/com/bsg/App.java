package com.bsg;

import com.bsg.process.AnagramProcess;
import com.bsg.process.impl.AnagramProcessImpl;
import com.sun.xml.internal.bind.v2.TODO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.Assert.assertEquals;


/**
 *
 *
 */
public class App
{
    final static Logger logger = LoggerFactory.getLogger(AnagramProcess.class);

    public static void main( String[] args ) {

        AnagramProcess anagramProcess = new AnagramProcessImpl();
        String word = "benj";
        String result = anagramProcess.processAnagram(word);
        //test case 1
        assertEquals(result, "44333322112222111111");
        logger.info("Word with the character length of " +  word.length() + " had "+ result + " anagram");

        //TODO write test case for reading dictionary file
    }
}
