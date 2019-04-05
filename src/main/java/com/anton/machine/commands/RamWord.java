package com.anton.machine.commands;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * Represent a word in ram or register.
 * Can be any number of bits long.
 */
@Slf4j
public class RamWord {

    private static final char BIN_ONE = '1';
    private static final char BIN_ZERO = '0';

    private int value = 0;

    /**
     * Get the value as decimal.
     *
     * @return decimal.
     */
    public int getValue(){
        return value;
    }

    /**
     * Get the value as binary string.
     *
     * @return binary string of expected length.
     */
    public String getBinary(){
        StringBuilder buf = new StringBuilder();
        int wordLength = Config.INSTANCE.getWordLength();
        Integer integer = value;
        buf.append(Integer.toBinaryString(value));
        while(buf.length() < wordLength){
            buf.insert(0,"0");
        }
        while(buf.length() > wordLength){
            buf.delete(0,1);
        }
        return buf.toString();
    }

    /**
     * Set the value from a binary string.
     *
     * @param binaryString value to set.
     */
    public void setBinary(String binaryString) {
        try {
            if (binaryString != null) {
                char[] chars = binaryString.toCharArray();
                int result = 0;
                for(char c: chars) {
                    // check each binary digit
                    if (c == BIN_ONE || c == BIN_ZERO) {
                        // shift result by power of two
                        result *= 2;
                        // Add one bit in lowest position
                        if (c == '1') {
                            result += 1;
                        }
                    }
                }
                value = result;
            }
        } catch (Throwable t) {
            log.debug("Error interpretting: '" + binaryString +
                    "', err=" + t.toString());
        }
    }

    public boolean isOverflow() {
        final int max = (int) Math.pow(2, Config.INSTANCE.getWordLength());
        boolean result = (value >= max || value < (-1 * max));
        return result;
    }

    public boolean isNegative() {
        return (value < 0);
    }
}
