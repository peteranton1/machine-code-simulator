package com.anton.machine.model.convert;

import com.anton.machine.commands.RamList;
import com.anton.machine.model.Address;
import com.anton.machine.model.Instruction;
import com.anton.machine.model.Line;

public class StoreAConverter implements InstructionConverter {

    /**
     * Apply a line against memory.
     * Override in implementations.
     *
     * @param line      the input line.
     * @param registers the registers.
     * @param ram       the ram.
     */
    @Override
    public void apply(Line line, RamList registers, RamList ram) {
        if (line != null && Instruction.STORE_A.equals(line.getInstruction())) {
            // Read value from register
            String valueStr = registers.read(
                    Address.A0000.getNibble());
            // write from register A value to ram
            ram.write(line.getAddress().getNibble(), valueStr);
        }
    }
}
