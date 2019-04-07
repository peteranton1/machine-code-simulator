package com.anton.machine.model.convert;

import com.anton.machine.commands.RamList;
import com.anton.machine.commands.RamUtils;
import com.anton.machine.model.Address;
import com.anton.machine.model.Instruction;
import com.anton.machine.model.Line;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JumpConverter implements InstructionConverter {
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
        if (line != null && Instruction.JUMP.equals(line.getInstruction())) {
            // get address to jump to
            String jumpAddressStr = line.getInstruction().getNibble();
            // convert string to int
            int jumpAddress = RamUtils.INSTANCE.stringToInt(jumpAddressStr);
            //write address to program counter in ram
            ram.setProgramCounter(jumpAddress);
        }
    }
}
