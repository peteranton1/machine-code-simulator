package com.anton.machine.model.convert;

import com.anton.machine.commands.RamList;
import com.anton.machine.model.Address;
import com.anton.machine.model.Argument;
import com.anton.machine.model.Instruction;
import com.anton.machine.model.Line;

public class ShiftAIConverter implements InstructionConverter {

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
        if (Instruction.SHIFT_AI.equals(instruction)) {
            // Read value from register
            String valueStr = registers.read(
                    Address.A0000.getCode());
            if(valueStr.length()>4){
                // shift left 5 bits
                valueStr = valueStr.substring(4);
                // add new value to lowest 4 bits.
                valueStr += argument.getCode();
            }
            // write value to register A
            registers.write(Address.A0000.getCode(), valueStr, comment);
        }
    }
}
