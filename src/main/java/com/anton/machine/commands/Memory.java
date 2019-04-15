package com.anton.machine.commands;

import com.anton.machine.model.Instruction;
import com.anton.machine.model.Line;
import com.anton.machine.model.Register;
import com.anton.machine.model.convert.InstructionExecutor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

import static java.util.Optional.ofNullable;

/**
 * Load a program into memory as a list.
 */
@Slf4j
public enum Memory {
    INSTANCE;

    public static final String DASH_8 = "--------";
    public static final String ONE = "1";
    private RamList registers = new RamList();
    private RamList ram = new RamList();
    private boolean haltExecuted = false;

    public int ramSize() {
        return ram.getSize();
    }

    public int registersSize() {
        return registers.getSize();
    }

    public void resetAndLoad(List<Line> lines) {
        reset();
        Assembler.INSTANCE.load(lines, registers, ram);
        resetProgramCounter();
    }

    public void setProgramCounter(int programCounter) {
        ram.setProgramCounter(programCounter);
    }

    public int getProgramCounter() {
        return ram.getProgramCounter();
    }

    public void resetProgramCounter() {
        ram.setProgramCounter(0);
    }

    public void reset() {
        haltExecuted = false;
        registers.clear();
        ram.clear();
        resetProgramCounter();
    }

    public int run() {
        reset();
        boolean done = false;
        int programCounter = ram.getProgramCounter();
        while (!done) {
            Instruction currentInstruction = step(ONE);
            programCounter = ram.getProgramCounter();
            if (isEndOfProgram(programCounter, currentInstruction)) {
                done = true;
                haltExecuted = true;
            }
        }
        log.info("Program exit at location: " +
                RamUtils.INSTANCE.intToString(programCounter));
        return programCounter;
    }

    private boolean isEndOfProgram(int programCounter, Instruction currentInstruction) {
        return programCounter > Config.INSTANCE.getMemMaxSize() ||
                Instruction.HALT.equals(currentInstruction) ||
                Instruction.NOOP.equals(currentInstruction);
    }

    public Instruction step(String params) {
        String trim = ofNullable(params).map(String::trim).orElse(ONE);
        int stepsCounter = 1;
        if (trim.length() > 0 ) {
            stepsCounter = Integer.parseInt(trim);
        }
        Instruction instruction = Instruction.HALT;
        if(!haltExecuted) {
            for (int i = 0; i < stepsCounter; i++) {
                int programCounter = ram.getProgramCounter();
                String programCounterStr = RamUtils.INSTANCE.intToString(programCounter);
                RamWord ramWord = ram.findOrAdd(programCounterStr);
                ram.setProgramCounter(programCounter + 1);
                instruction = Instruction.parse(ramWord.readValue());
                InstructionExecutor.executeStep(ramWord, registers, ram);
            }
        } else {
            printProgramCounter();
        }
        return instruction;
    }

    public void memory() {
        memory(null);
    }

    public void memory(String params) {
        String trim = ofNullable(params).map(String::trim).orElse("");
        if (trim.length() > 0 && trim.length() < 4) {
            if (trim.charAt(0) == 'p') {
                printProgramCounter();
            } else {
                printRegister(Register.find(trim).getCode());
            }
        } else if (trim.length() >= 4) {
            printLocation(params.substring(0, 4));
        } else {
            printAll();
        }
    }

    private void printLocation(String addressStr) {
        RamWord ramWord = ram.findOrAdd(addressStr);
        log.info("Loc: " + ramWord.toString());
    }

    private void printRegister(String addressStr) {
        RamWord ramWord = registers.findOrAdd(addressStr);
        log.info("Reg: " + ramWord.toString());
    }

    private void printProgramCounter() {
        log.info("PC: " + ram.getProgramCounter());
    }

    private void printAll() {
        log.info(formatCell("Address", "Value", "Comment"));
        log.info(formatCell(DASH_8, DASH_8, DASH_8));
        for (RamWord ramWord : ram.getList()) {
            log.info(ramWord.toString());
        }
        log.info(formatCell("-->> ProgramCounter", "" + ram.getProgramCounter(), "<<--"));
        log.info(formatCell(DASH_8, DASH_8, DASH_8));
        log.info(formatCell("Register", "Value", DASH_8));
        for (RamWord ramWord : registers.getList()) {
            log.info(ramWord.toString());
        }
        log.info(formatCell(DASH_8, DASH_8, DASH_8));
    }

    private String formatCell(String s1, String s2, String s3) {
        return String.format("%15s: %8s; %s", s1, s2, s3);
    }
}
