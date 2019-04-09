package com.anton.machine.commands;

import com.anton.machine.model.Instruction;
import com.anton.machine.model.Line;
import com.anton.machine.model.convert.InstructionConverter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Load a program into memory as a list.
 */
@Slf4j
public enum Memory {
    INSTANCE;

    public static final String DASH_8 = "--------";
    private RamList registers = new RamList();
    private RamList ram = new RamList();

    public void resetAndLoad(List<Line> lines) {
        reset();
        Assembler.INSTANCE.load(lines, registers, ram);
    }

    public void reset() {
        registers.clear();
        ram.clear();
    }

    public int run(){
        reset();
        boolean done = false;
        int programCounter = ram.getProgramCounter();
        while(!done){
            programCounter = ram.getProgramCounter();
            if(programCounter > Config.INSTANCE.getMemMaxSize()||
                    Instruction.HALT.equals( step())){
                done = true;
            }
        }
        log.info("Program exist at location: " +
                RamUtils.INSTANCE.intToString(programCounter));
        return programCounter;
    }

    public Instruction step() {
        int programCounter = ram.getProgramCounter();
        String programCounterStr = RamUtils.INSTANCE.intToString(programCounter);
        RamWord ramWord = ram.findOrAdd(programCounterStr);
        ram.setProgramCounter(programCounter+1);
        Instruction instruction = Instruction.parse(ramWord.readValue());
        InstructionConverter.executeStep(ramWord,registers,ram);
        return instruction;
    }

    public void memory(String params) {
        if (params.trim().length() >= 4) {
            printLocation(params.substring(0, 4));
        } else {
            printAll();
        }
    }

    private void printLocation(String addressStr) {
        RamWord ramWord = ram.findOrAdd(addressStr);
        log.info(ramWord);
    }

    private void printAll() {
        log.info(formatCell("Address", "Value", "Comment"));
        log.info(formatCell(DASH_8, DASH_8, DASH_8));
        for (RamWord ramWord : ram.getList()) {
            log.info(ramWord);
        }
        log.info(formatCell(DASH_8, DASH_8, DASH_8));
        for (RamWord ramWord : registers.getList()) {
            log.info(ramWord);
        }
        log.info(formatCell(DASH_8, DASH_8, DASH_8));
    }

    private String formatCell(String s1, String s2, String s3) {
        return String.format("%10s: %8s; %s", s1, s2, s3);
    }
}
