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
        if (argument == null || argument.getCode() == null) {
            throw new IllegalArgumentException("Bad argument to getRegisters");
        }
        final String REG_EXAMPLE = "00";
        final int REG_LEN = REG_EXAMPLE.length();
        final int NIB_LEN = (REG_LEN * 2);
        final String nibble = REG_EXAMPLE + REG_EXAMPLE + argument.getCode();

        final String leftBits = nibble
                .substring(nibble.length() - NIB_LEN,
                        (nibble.length() - NIB_LEN) + REG_LEN);

        final String rightBits = nibble
                .substring(nibble.length() - REG_LEN, nibble.length());

        log.debug("Test: getRegisters(" + argument +
                ")=(" + leftBits + ")(" + rightBits + ")");

        return new Register[]{
                Register.find(leftBits),
                Register.find(rightBits)
        };
    }
}
