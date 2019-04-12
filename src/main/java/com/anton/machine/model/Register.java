/*
 * Copyright (c) Experian, 2019. All rights reserved.
 */
package com.anton.machine.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Register {
    REGISTER_A("00", "A"),
    REGISTER_B("01", "B"),
    REGISTER_C("10", "C"),
    REGISTER_D("11", "D");

    private String code;
    private String letter;

    public static Register find(String bits) {
        for (Register register : values()) {
            if (register.getCode().equals(bits) ||
                    register.getLetter().equalsIgnoreCase(bits)) {
                return register;
            }
        }
        throw new RegisterException("Illegal Register bits: " + bits);
    }
}
