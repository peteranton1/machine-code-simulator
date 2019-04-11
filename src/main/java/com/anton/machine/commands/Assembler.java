package com.anton.machine.commands;

import com.anton.machine.model.Line;
import com.anton.machine.model.convert.InstructionExecutor;

import java.util.List;

/**
 * Reads each line and inserts into memory.
 */
public enum Assembler {
    INSTANCE;

    public void load(List<Line> lines, RamList registers, RamList ram){
        for(Line line: lines){
            // load ram and registers using list
            // of instruction converters.
            InstructionExecutor.load(line, ram);
        }
    }
}
