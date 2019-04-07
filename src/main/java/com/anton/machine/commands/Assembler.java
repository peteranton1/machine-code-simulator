package com.anton.machine.commands;

import com.anton.machine.model.Line;
import com.anton.machine.model.convert.InstructionConverter;

import java.util.List;

/**
 * Reads each line and inserts into memory.
 */
public enum Assembler {
    INSTANCE;

    public void assemble(List<Line> lines, RamList registers, RamList ram){
        for(Line line: lines){
            // load ram and registers using list
            // of instruction converters.
            InstructionConverter.findAndConvert(line, registers, ram);
        }
    }


}
