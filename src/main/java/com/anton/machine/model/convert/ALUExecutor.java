/*
 * Copyright (c) Experian, 2019. All rights reserved.
 */
package com.anton.machine.model.convert;

import com.anton.machine.commands.Config;
import com.anton.machine.commands.RamList;
import com.anton.machine.commands.RamUtils;
import com.anton.machine.model.Argument;
import com.anton.machine.model.Instruction;
import com.anton.machine.model.Register;

/**
 * Performs Arithmetic Logic Unit operations.
 */
public abstract class ALUExecutor implements InstructionExecutor {

    abstract int performOperation(int valueLeft, int valueRight);

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
            Register[] fromTo = ExecutorHelper.INSTANCE.getRegisters(argument);
            //read value from reg B
            String valueLeftStr = registers.read(fromTo[0].getCode());
            //read value from reg A
            String valueRightStr = registers.read(fromTo[1].getCode());
            //convert B to int
            int valueLeft = RamUtils.INSTANCE.stringToInt(valueLeftStr);
            //convert A to int
            int valueRight = RamUtils.INSTANCE.stringToInt(valueRightStr);
            //add B to A
            valueRight = performOperation(valueRight, valueLeft);
            // Set flag if overflow
            if (valueRight > Config.INSTANCE.getWordMaxValue()) {
                registers.setOverflow(fromTo[1].getCode());
            }
            //convert value A into string
            valueRightStr = RamUtils.INSTANCE.intToString(valueRight);
            //write value A to register A
            registers.write(fromTo[1].getCode(),
                    valueRightStr, comment);
        }
    }
}
