package com.anton.machine.model.convert;

import com.anton.machine.commands.RamList;
import com.anton.machine.commands.RamUtils;
import com.anton.machine.model.Argument;
import com.anton.machine.model.Instruction;
import lombok.extern.slf4j.Slf4j;


/**
 * Executes JUMP instructions in machine code.
 */
@Slf4j
public class JumpExecutor implements InstructionExecutor {

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
        if (Instruction.JUMP.equals(instruction)) {
            // get address to jump to
            String jumpAddressStr = argument.getCode();
            // convert string to int
            int jumpAddress = RamUtils.INSTANCE.stringToInt(jumpAddressStr);
            //write address to program counter in ram
            ram.setProgramCounter(jumpAddress);
        }
    }
}
