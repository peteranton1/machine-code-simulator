/*
 * Copyright (c) Experian, 2019. All rights reserved.
 */
package com.anton.machine.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Register {
    REGISTER_A("00"),
    REGISTER_B("01"),
    REGISTER_C("10"),
    REGISTER_D("11");

    private String code;

    public static Register find(String bits){
        for(Register register: values()){
            if(register.getCode().equals(bits)){
                return register;
            }
        }
        throw new RegisterException("Illegal Register bits: " + bits);
    }
}
