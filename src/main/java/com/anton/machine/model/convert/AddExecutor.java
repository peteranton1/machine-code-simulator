package com.anton.machine.model.convert;

/**
 * Executes ADD instructions in machine code.
 */
public class AddExecutor extends ALUExecutor {

    @Override
    int performOperation(int valueLeft, int valueRight) {
        return valueRight + valueLeft;
    }
}
