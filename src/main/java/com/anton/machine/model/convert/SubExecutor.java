package com.anton.machine.model.convert;

/**
 * Executes SUB instructions in machine code.
 */
public class SubExecutor extends ALUExecutor {

    @Override
    int performOperation(int valueLeft, int valueRight) {
        return valueRight - valueLeft;
    }
}
