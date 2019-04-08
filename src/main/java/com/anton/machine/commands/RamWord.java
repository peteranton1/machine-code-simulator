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

    /**
     * adds the value of the binary string to the member
     * variable called value.
     *
     * @param binaryString the value as a binary string.
     */
    public void add(String binaryString) {
        this.value += RamUtils.INSTANCE.stringToInt(binaryString);
    }

    /**
     * subtract the value of the binary string to the member
     * variable called value.
     *
     * @param binaryString the value as a binary string.
     */
    public void subtract(String binaryString) {
        this.value -= RamUtils.INSTANCE.stringToInt(binaryString);
    }
    /**
     * multiply the value of the binary string to the member
     * variable called value.
     *
     * @param binaryString the value as a binary string.
     */
    public void multiply(String binaryString) {
        this.value *= RamUtils.INSTANCE.stringToInt(binaryString);
    }
    /**
     * divides the value of the binary string to the member
     * variable called value.
     *
     * @param binaryString the value as a binary string.
     */
    public void divide(String binaryString) {
        this.value /= RamUtils.INSTANCE.stringToInt(binaryString);
    }

    /**
     * Returns true if last operation overflowed.
     *
     * @return true if overflowed.
     */
    public boolean isOverflow() {
        final int max = (int) Math.pow(2, Config.INSTANCE.getWordLength());
        boolean result = (value >= max || value < (-1 * max));
        return result;
    }

    /**
     * Returns true if result is negative.
     *
     * @return true if negative.
     */
    public boolean isNegative() {
        return (value < 0);
    }

    @Override
    public String toString() {
        return String.format("[%8s]:[%8s] %s",
                RamUtils.INSTANCE.intToString(address),
                RamUtils.INSTANCE.intToString(value),
                getComment());
    }
}
