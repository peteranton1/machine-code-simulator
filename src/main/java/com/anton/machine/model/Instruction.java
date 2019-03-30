package com.anton.machine.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Enum for Machine Code Instructions.
 */
@AllArgsConstructor
public enum Instruction {
    NOOP("0000", "; No operation"),
    LOAD_A("0001", "; Read RAM location into Register A"),
    LOAD_B("0010", "; Read RAM location into Register B"),
    STORE_A("0010", "; Write register A into RAM location"),
    LOAD_AI("0011", "; Read value into Register A"),
    ADD("0100", "; Add two registers, store to second register."),
    SUB("0101", "; Subtract two registers, store to second register."),
    MULT("0110", "; Multiply two registers, store to second register."),
    DIV("0111", "; Divide two registers, store to second register."),
    JUMP("1100", "; Jump program to new RAM location."),
    JUMP_NEG("1101", "; Jump program if A register negative."),
    JUMP_OVER("1110", "; Jump program if A register overflowed."),
    HALT("1111", "; Stop program.");

    @Getter
    private String nibble;
    @Getter
    private String comment;

    public static Instruction parse(String nibble) {
        for (Instruction instruction : values()) {
            if (instruction.getNibble().equals(nibble)) {
                return instruction;
            }
        }
        return NOOP;
    }
}
