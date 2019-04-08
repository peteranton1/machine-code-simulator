package com.anton.machine.commands;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum RamUtils {
    INSTANCE;

    private static final char BIN_ONE = '1';
    private static final char BIN_ZERO = '0';

    /**
     * Converts integer value to a binary string.
     *
     * @param value integer.
     * @return string e.g. '1010'.
     */
    public String intToString(int value) {
        StringBuilder buf = new StringBuilder();
        int wordLength = Config.INSTANCE.getWordLength();
        buf.append(Integer.toBinaryString(value));
        while (buf.length() < wordLength) {
            buf.insert(0, "0");
        }
        while (buf.length() > wordLength) {
            buf.delete(0, 1);
        }
        return buf.toString();
    }

    /**
     * execute binary string to int.
     *
     * @param binaryString value to execute.
     * @return the int value.
     */
    public int stringToInt(String binaryString) {
        try {
            if (binaryString != null) {
                char[] chars = binaryString.toCharArray();
                int result = 0;
                for (char c : chars) {
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
                return result;
            }
        } catch (Throwable t) {
            log.debug("Error interpretting: '" + binaryString +
                    "', err=" + t.toString());
        }
        return 0;
    }

}
