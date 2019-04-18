package com.anton.machine.model.convert;

import com.anton.machine.commands.RamList;
import com.anton.machine.model.Address;
import com.anton.machine.model.Argument;
import com.anton.machine.model.Instruction;

/**
 * Executes STORE_B instructions in machine code.
 */
public class StoreBExecutor implements InstructionExecutor {

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
        if (Instruction.STORE_B.equals(instruction)) {
            // Read value from register
            String valueStr = registers.read(
                    Address.A0001.getCode());
            String comment1 = String.format(
                    "Value %s", valueStr);
            // write from register A value to ram
            ram.write(argument.getCode(), valueStr, comment1);
        }
    }
}
