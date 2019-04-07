package com.anton.machine.model.convert;

import com.anton.machine.commands.RamList;
import com.anton.machine.model.Instruction;
import com.anton.machine.model.Line;

import java.util.Arrays;
import java.util.List;

public interface InstructionConverter {


    /**
     * Convert a line into memory.
     * Override in implementations.
     *
     * @param line      the input line.
     * @param registers the registers.
     * @param ram       the ram.
     */
    void apply(Line line, RamList registers, RamList ram);

    /**
     * For a line, find and apply the correct converter.
     *
     * @param line      the input line.
     * @param registers the registers.
     * @param ram       the ram.
     */
    static void findAndConvert(Line line, RamList registers, RamList ram) {
        Instruction
                .getConverters()
                .forEach(converter ->
                        converter.apply(line, registers, ram));
    }
}
