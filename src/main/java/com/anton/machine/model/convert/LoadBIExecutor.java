package com.anton.machine.model.convert;

import com.anton.machine.commands.RamList;
import com.anton.machine.model.Address;
import com.anton.machine.model.Argument;
import com.anton.machine.model.Instruction;

/**
 * Executes LOAD_BI instructions in machine code.
 */
public class LoadBIExecutor implements InstructionExecutor {

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
        if (Instruction.LOAD_BI.equals(instruction)) {
            registers.write(Address.A0001.getCode(), argument.getCode(), comment);
        }
    }
}
