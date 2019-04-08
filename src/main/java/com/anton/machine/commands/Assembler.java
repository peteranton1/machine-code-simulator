package com.anton.machine.commands;

import com.anton.machine.model.Line;
import com.anton.machine.model.convert.InstructionConverter;

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
            InstructionConverter.load(line, registers, ram);
        }
    }

    public void apply(List<Line> lines, RamList registers, RamList ram){
        for(Line line: lines){
            // load ram and registers using list
            // of instruction converters.
            InstructionConverter.load(line, registers, ram);
        }
    }


}
