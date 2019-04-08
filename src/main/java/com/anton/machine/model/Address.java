package com.anton.machine.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum for RAM Addresses.
 */
@AllArgsConstructor
public enum Address {
    A0000("0000"),
    A0001("0001"),
    A0010("0010"),
    A0011("0011"),
    A0100("0100"),
    A0101("0101"),
    A0110("0110"),
    A0111("0111"),
    A1000("1000"),
    A1001("1001"),
    A1010("1010"),
    A1011("1011"),
    A1100("1100"),
    A1101("1101"),
    A1110("1110"),
    A1111("1111")
    ;
    @Getter
    private String code;

    public static Address parse(String code) {
        for (Address address : values()) {
            if (address.getCode().equals(code)) {
                return address;
            }
        }
        return A0000;
    }
}
