package com.anton.machine.model.convert;

import com.anton.machine.commands.RamList;
import com.anton.machine.commands.RamUtils;
import com.anton.machine.model.Address;
import com.anton.machine.model.Argument;
import com.anton.machine.model.Instruction;
import lombok.extern.slf4j.Slf4j;

/**
 * Executes JUMP_OVER instructions in machine code.
 */
@Slf4j
public class JumpOverExecutor implements InstructionExecutor {

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
        if (Instruction.ADD.equals(instruction)) {
            // get address to jump to
            String jumpAddressStr = argument.getCode();
            // convert string to int
            int jumpAddress = RamUtils.INSTANCE.stringToInt(jumpAddressStr);
            //read value from reg B

            //Check if A negs
            if (registers.isOverflow(Address.A0000.getCode())) {
                //write address to program counter in ram
                ram.setProgramCounter(jumpAddress);
            }
        }
    }
}
