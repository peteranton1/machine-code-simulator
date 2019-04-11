package com.anton.machine.model.convert;

/**
 * Executes MULT instructions in machine code.
 */
public class MultExecutor extends ALUExecutor {

    @Override
    int performOperation(int valueLeft, int valueRight) {
        return valueRight * valueLeft;
    }
}
