package com.anton.machine.commands;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Represent a word in ram or register.
 * Can be any number of bits long.
 */
@Slf4j
public class RamWord {

    @Getter
    @Setter
    private int address = -1;
    @Getter
    @Setter
    private int value = 0;
    @Getter
    @Setter
    private boolean negative = false;
    @Getter
    @Setter
    private boolean overflow = false;
    @Getter
    @Setter
    private String comment = "";


    /**
     * Get the address as binary string.
     *
     * @return binary string of expected length.
     */
    public String readAddress() {
        return RamUtils.INSTANCE.intToString(address);
    }

    /**
     * Load the address as a binary string.
     * @param binaryString address to load.
     */
    public void loadAddress(String binaryString) {
        address = RamUtils.INSTANCE.stringToInt(binaryString);
    }

    /**
     * Get the value as binary string.
     *
     * @return binary string of expected length.
     */
    public String readValue() {
        return RamUtils.INSTANCE.intToString(value);
    }

    /**
     * Load the value as a binary string.
     * @param binaryString value to load.
     */
    public void loadValue(String binaryString) {
        value = RamUtils.INSTANCE.stringToInt(binaryString);
    }

    @Override
    public String toString() {
        return String.format("[%3d][%8s]:[%8s] [%s%s] %s",
                (address),
                RamUtils.INSTANCE.intToString(address),
                RamUtils.INSTANCE.intToString(value),
                (isOverflow()?"O":" "),
                (isNegative()?"N":" "),
                getComment());
    }
}
