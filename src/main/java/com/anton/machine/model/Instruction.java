package com.anton.machine.model;

import com.anton.machine.model.convert.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

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

    private static Map<Instruction, InstructionExecutor> converterMap = loadMap();

    public static InstructionExecutor getConverter(Instruction instruction){
        return converterMap.get(instruction);
    }

    private static Map<Instruction, InstructionExecutor> loadMap() {
        Map<Instruction, InstructionExecutor> map = new HashMap<>();
        map.put(NOOP, new NoopExecutor());
        map.put(LOAD_A, new LoadAExecutor());
        map.put(LOAD_B, new LoadBExecutor());
        map.put(STORE_A, new StoreAExecutor());
        map.put(STORE_B, new StoreBExecutor());
        map.put(LOAD_AI, new LoadAIExecutor());
        map.put(LOAD_BI, new LoadBIExecutor());
        map.put(SHIFT_AI, new ShiftAIExecutor());
        map.put(ADD, new AddExecutor());
        map.put(SUB, new SubExecutor());
        map.put(MULT, new MultExecutor());
        map.put(DIV, new DivExecutor());
        map.put(JUMP, new JumpExecutor());
        map.put(JUMP_NEG, new JumpNegExecutor());
        map.put(JUMP_OVER, new JumpOverExecutor());
        map.put(HALT, new HaltExecutor());
        return map;
    }

    @Getter
    private String code;
    @Getter
    private String mneumonic;
    @Getter
    private String comment;

    public static Instruction parse(String codeOrMneumonic) {
        for (Instruction instruction : values()) {
            if (codeOrMneumonic.startsWith(instruction.getCode()) ||
                    instruction.getMneumonic().equalsIgnoreCase(codeOrMneumonic) ) {
                return instruction;
            }
        }
        return NOOP;
    }
}
