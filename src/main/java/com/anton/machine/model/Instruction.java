package com.anton.machine.model;

import com.anton.machine.commands.RamList;
import com.anton.machine.model.convert.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * Enum for Machine Code Instructions.
 */
@AllArgsConstructor
public enum Instruction {
    NOOP("0000", "NOOP", "; No operation"),
    LOAD_A("0001", "LOAD_A", "; Read RAM location %s into Register A"),
    LOAD_B("0010", "LOAD_B", "; Read RAM location %s into Register B"),
    STORE_A("0011", "STORE_A", "; Write register A into RAM location %s "),
    STORE_B("1011", "STORE_B", "; Write register B into RAM location %s "),
    LOAD_AI("0100", "LOAD_AI", "; Load a 4 bit value %s into Register A"),
    LOAD_BI("1000", "LOAD_BI", "; Load a 4 bit value %s into Register B"),
    SHIFT_AI("0101", "SHIFT_AI", "; Shift Register A 4 bits then add another 4 bit value %s"),
    ADD("0110", "ADD", "; Add two registers, store to second register."),
    SUB("0111", "SUB", "; Subtract two registers, store to second register."),
    MULT("1001", "MULT", "; Multiply two registers, store to second register."),
    DIV("1010", "DIV", "; Divide two registers, store to second register."),
    JUMP("1100", "JUMP", "; Jump program to new RAM location %s."),
    JUMP_NEG("1101", "JUMP_NEG", "; Jump program if A register negative."),
    JUMP_OVER("1110", "JUMP_OVER", "; Jump program if A register overflowed."),
    HALT("1111", "HALT", "; Stop program.");

    @Getter
    private static List<InstructionConverter> converters = Arrays.asList(
            new NoopConverter(),
            new LoadAConverter(),
            new LoadBConverter(),
            new StoreAConverter(),
            new StoreBConverter(),
            new LoadAIConverter(),
            new LoadBIConverter(),
            new ShiftAIConverter(),
            new AddConverter(),
            new SubConverter(),
            new MultConverter(),
            new DivConverter(),
            new JumpConverter(),
            new JumpNegConverter(),
            new JumpOverConverter(),
            new HaltConverter()
    );

    @Getter
    private String nibble;
    @Getter
    private String mneumonic;
    @Getter
    private String comment;

    public static Instruction parse(String nibbleOrMneumonic) {
        for (Instruction instruction : values()) {
            if (instruction.getNibble().equals(nibbleOrMneumonic) ||
                    instruction.getMneumonic().equalsIgnoreCase(nibbleOrMneumonic) ) {
                return instruction;
            }
        }
        return NOOP;
    }
}
