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
    private static final String K_WORD_MAX_VALUE = "word-max-value";
    private static final String K_WORD_MIN_VALUE = "word-min-value";

    private Properties properties = new Properties();

    public int getWordLength() {
        return getInt(K_WORD_LENGTH, 8);
    }

    public int getWordMaxValue() {
        return getInt(K_WORD_MAX_VALUE, 127);
    }

    public int getWordMinValue() {
        return getInt(K_WORD_MIN_VALUE, -128);
    }

    public int getInt(String key, int defaultValue) {
        int value = defaultValue;
        String valueStr = properties.getProperty(key);
        try {
            if (null != valueStr) {
                value = Integer.parseInt(valueStr);
            }
        } catch (Throwable t) {
            log.error("Error reading property: " +
                    key +
                    "=" + valueStr +
                    ", err=" + t.toString());
        }
        return value;
    }
}
