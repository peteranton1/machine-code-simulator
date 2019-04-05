package com.anton.machine.commands;

import lombok.extern.slf4j.Slf4j;

import java.util.Properties;


/**
 * Holds configuration parameters.
 */
@Slf4j
public enum Config {
    INSTANCE;

    private static final String K_WORD_LENGTH = "word-length";

    private Properties properties = new Properties();

    public int getWordLength() {
        return getInt(K_WORD_LENGTH);
    }

    public int getInt(String key) {
        int wordLength = 8;
        String wordLengthString =
                properties.getProperty(key);
        try {
            if (null != wordLengthString) {
                wordLength = Integer.parseInt(wordLengthString);
            }
        } catch (Throwable t) {
            log.error("Error reading property: " +
                    key +
                    "=" + wordLengthString +
                    ", err=" + t.toString());
        }
        return wordLength;
    }
}
