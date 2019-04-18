package com.anton.machine.commands;

import com.anton.machine.model.Instruction;
import com.anton.machine.model.Line;
import com.anton.machine.model.Register;
import com.anton.machine.model.convert.InstructionExecutor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
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
        haltExecuted = false;
        ram.setProgramCounter(0);
    }

    public void reset() {
        registers.clear();
        ram.clear();
        resetProgramCounter();
    }

    public int run() {
        resetProgramCounter();
        int programCounter = ram.getProgramCounter();
        while (!haltExecuted) {
            step(ONE);
            programCounter = ram.getProgramCounter();
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
        if (trim.length() > 0) {
            stepsCounter = Integer.parseInt(trim);
        }
        Instruction instruction = Instruction.HALT;
        for (int i = 0; i < stepsCounter && !haltExecuted; i++) {
            int programCounter = ram.getProgramCounter();
            String programCounterStr = RamUtils.INSTANCE.intToString(programCounter);
            RamWord ramWord = ram.findOrAdd(programCounterStr);
            ram.setProgramCounter(programCounter + 1);
            instruction = Instruction.parse(ramWord.readValue());
            InstructionExecutor.executeStep(ramWord, registers, ram);
            if (isEndOfProgram(programCounter, instruction)) {
                haltExecuted = true;
            }
        }
        return instruction;
    }


    /**
     * Display all memory locations.
     */
    public void memory() {
        memory(new String[]{""});
    }

    /**
     * Display (and set) a memory location or locations.
     *
     * @param args args[1] location, [args[2] value to set].
     */
    public void memory(String[] args) {
        log.debug("Args: " + Arrays.toString(args));
        String p1 = "";
        if(args.length>1){
            p1 = ofNullable(args[1]).map(String::trim).orElse("");
        }
        String p2 = "";
        int p2Int = 0;
        boolean p2Available = false;
        if(args.length>2){
            p2 = ofNullable(args[2]).map(String::trim).orElse("");
            if(p2.length()>0) {
                p2Int = Integer.parseInt(p2);
                p2Available = true;
            }
        }

        if (p1.length() > 0 && p1.length() < 4) {
            if (p1.charAt(0) == 'p') {
                if(p2Available){
                    setProgramCounter(p2Int);
                }
                printProgramCounter();
            } else {
                final String addressStr = Register.find(p1).getCode();
                if(p2Available){
                    loadRegister(addressStr, p2Int);
                }
                printRegister(addressStr);
            }
        } else if (p1.length() >= 4) {
            final String addressStr = args[1].substring(0, 4);
            if(p2Available){
                loadLocation(addressStr, p2Int);
            }
            printLocation(addressStr);
        } else {
            printAll();
        }
    }

    private void loadLocation(String addressStr, int value) {
        RamWord ramWord = ram.findOrAdd(addressStr);
        ramWord.setValue(value);
    }

    private void printLocation(String addressStr) {
        RamWord ramWord = ram.findOrAdd(addressStr);
        log.info("Loc: " + ramWord.toString());
    }

    private void loadRegister(String addressStr, int value) {
        RamWord ramWord = registers.findOrAdd(addressStr);
        ramWord.setValue(value);
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
