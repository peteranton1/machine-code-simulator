package com.anton.machine.model.convert;

import com.anton.machine.commands.RamList;
import com.anton.machine.model.Address;
import com.anton.machine.model.Instruction;
import com.anton.machine.model.Line;

public class LoadBIConverter implements  InstructionConverter{

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
        if(line != null && Instruction.LOAD_BI.equals(line.getInstruction())){
            registers.write(Address.A0001.getNibble(),
                    line.getInstruction().getNibble());
        }
    }
}
