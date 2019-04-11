package com.anton.machine.model.convert;

import lombok.extern.slf4j.Slf4j;


/**
 * Executes DIV instructions in machine code.
 */
@Slf4j
public class DivExecutor extends ALUExecutor {

    @Override
    int performOperation(int valueLeft, int valueRight) {
        //divide A by B
        try {
            valueRight = valueRight / valueLeft;
        } catch (Exception e) {
            log.error("Error : " + e);
            valueRight = 0;
        }
        return valueRight;
    }
}
