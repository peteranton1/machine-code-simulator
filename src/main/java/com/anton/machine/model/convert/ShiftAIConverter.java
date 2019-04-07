package com.anton.machine.model.convert;

import com.anton.machine.commands.RamList;
import com.anton.machine.model.Address;
import com.anton.machine.model.Instruction;
import com.anton.machine.model.Line;

public class ShiftAIConverter implements InstructionConverter {
    /**
     * Convert a line into memory.
     * Override in implementations.
     *
     * @param line      the input line.
     * @param registers the registers.
     * @param ram       the ram.
     */
    @Override
    public void apply(Line line, RamList registers, RamList ram) {
        if (line != null && Instruction.SHIFT_AI.equals(line.getInstruction())) {
            // Read value from register
            String valueStr = registers.read(
                    Address.A0000.getNibble());
            if(valueStr.length()>4){
                // shift left 5 bits
                valueStr = valueStr.substring(4);
                // add new value to lowest 4 bits.
                valueStr += line.getInstruction().getNibble();
            }
            // write value to register A
            registers.write(Address.A0000.getNibble(), valueStr);
        }
    }
}
