package com.anton.machine.commands;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class RamList {
    private List<RamWord> ram = new LinkedList<>();

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

    public int getSize() {
        return this.ram.size();
    }
}
