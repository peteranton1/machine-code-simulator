/*
 * Copyright (c) Experian, 2019. All rights reserved.
 */
package com.anton.machine.model.convert;

import com.anton.machine.model.Argument;
import com.anton.machine.model.Register;
import lombok.extern.slf4j.Slf4j;

/**
 * P
 */
@Slf4j
public enum ExecutorHelper {
    INSTANCE;

    public Register[] getRegisters(Argument argument) {
        String leftBits = argument.getCode().substring(0, 2);
        String rightBits = argument.getCode().substring(2, 4);
        log.debug("Test: getRegisters(" + argument +
                ")=(" + leftBits + ")(" + rightBits + ")");
        return new Register[]{
                Register.find(leftBits),
                Register.find(rightBits)
        };
    }
}
