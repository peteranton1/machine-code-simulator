package com.anton.machine.commands;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;


public class RamList {
    private List<RamWord> ram = new LinkedList<>();

    @Getter
    @Setter
    private int programCounter = 0;



    public void clear(){
        ram.clear();
        programCounter = 0;
    }

    /**
     * Finds or adds the address to memory and
     * returns the address.
     *
     * @param addressBinary the memory location to find.
     * @return the memory location.
     */
    private RamWord findOrAdd(String addressBinary) {
        int address = RamUtils.INSTANCE.stringToInt(addressBinary);
        RamWord ramWord = ram.stream()
                .filter(r -> r.getAddress() == address)
                .findFirst().orElse(new RamWord());
        if (ramWord.getAddress() < 0) {
            ram.add(ramWord);
        }
        return ramWord;
    }

    /**
     * Write a value to an address in memory.
     *
     * @param addressBinary address to write to.
     * @param valueBinary   value to write at address.
     */
    public void write(String addressBinary, String valueBinary) {
        RamWord ramWord = findOrAdd(addressBinary);
        ramWord.loadAddress(addressBinary);
        ramWord.loadValue(valueBinary);
    }

    /**
     * Read a value from memory at an address.
     *
     * @param addressBinary address to read from.
     * @return value at that address.
     */
    public String read(String addressBinary) {
        RamWord ramWord = findOrAdd(addressBinary);
        ramWord.loadAddress(addressBinary);
        return ramWord.readValue();
    }

    /**
     * Test if a value from memory at an address is negative.
     *
     * @param addressBinary address to read from.
     * @return isNegative at that address.
     */
    public boolean isNegative(String addressBinary) {
        RamWord ramWord = findOrAdd(addressBinary);
        ramWord.loadAddress(addressBinary);
        return ramWord.isNegative();
    }

    /**
     * Set a value from memory at an address to negative.
     *
     * @param addressBinary address to read from.
     * @return isNegative at that address.
     */
    public void setNegative(String addressBinary) {
        RamWord ramWord = findOrAdd(addressBinary);
        ramWord.loadAddress(addressBinary);
        ramWord.setNegative(true);
    }

    /**
     * Test if a value from memory at an address is overflow.
     *
     * @param addressBinary address to read from.
     * @return isOverflow at that address.
     */
    public boolean isOverflow(String addressBinary) {
        RamWord ramWord = findOrAdd(addressBinary);
        ramWord.loadAddress(addressBinary);
        return ramWord.isOverflow();
    }

    /**
     * Set a value from memory at an address to negative.
     *
     * @param addressBinary address to read from.
     * @return isNegative at that address.
     */
    public void setOverflow(String addressBinary) {
        RamWord ramWord = findOrAdd(addressBinary);
        ramWord.loadAddress(addressBinary);
        ramWord.setOverflow(true);
    }

    public int getSize() {
        return this.ram.size();
    }

    public List<RamWord> getList() {
        return this.ram;
    }

    @Override
    public String toString() {
        return ram.toString();
    }
}
