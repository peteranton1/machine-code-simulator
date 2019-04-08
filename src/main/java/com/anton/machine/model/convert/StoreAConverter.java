package com.anton.machine.model.convert;

import com.anton.machine.commands.RamList;
import com.anton.machine.model.Address;
import com.anton.machine.model.Argument;
import com.anton.machine.model.Instruction;
import com.anton.machine.model.Line;

public class StoreAConverter implements InstructionConverter {

    /**
     * Execute a line of a program.
     *
     * @param instruction the input instruction.
     * @param argument    the input argument.
     * @param comment     the input comment.
     * @param registers   the registers.
     * @param ram         the ram.
     */
    @Override
    public void execute(Instruction instruction,
                        Argument argument,
                        String comment,
                        RamList registers,
                        RamList ram) {
        if (Instruction.STORE_A.equals(instruction)) {
            // Read value from register
            String valueStr = registers.read(
                    Address.A0000.getCode());
            // write from register A value to ram
            ram.write(argument.getCode(), valueStr, comment);
        }
    }
}
