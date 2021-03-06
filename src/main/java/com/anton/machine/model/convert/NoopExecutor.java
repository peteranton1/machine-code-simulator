package com.anton.machine.model.convert;

import com.anton.machine.commands.RamList;
import com.anton.machine.model.Argument;
import com.anton.machine.model.Instruction;

/**
 * Executes NOOP instructions in machine code.
 */
public class NoopExecutor implements InstructionExecutor {

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
        if (Instruction.NOOP.equals(instruction)) {
//            registers.write(Address.A0000.getCode(),
//                    line.getInstruction().getCode());
        }
    }
}
