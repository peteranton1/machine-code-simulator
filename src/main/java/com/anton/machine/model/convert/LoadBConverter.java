package com.anton.machine.model.convert;

import com.anton.machine.commands.RamList;
import com.anton.machine.model.Address;
import com.anton.machine.model.Argument;
import com.anton.machine.model.Instruction;
import com.anton.machine.model.Line;

public class LoadBConverter implements InstructionConverter {

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
                        RamList ram){
        if (Instruction.LOAD_B.equals(instruction)) {
            // Read value from ram
            String valueStr = ram.read(argument.getCode());
            // write to register A value from ram
            registers.write(Address.A0001.getCode(), valueStr, comment);
        }
    }
}
