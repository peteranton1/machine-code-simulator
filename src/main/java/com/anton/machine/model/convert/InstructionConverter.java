package com.anton.machine.model.convert;

import com.anton.machine.commands.RamList;
import com.anton.machine.commands.RamUtils;
import com.anton.machine.model.Instruction;
import com.anton.machine.model.Line;

import java.util.Arrays;
import java.util.List;

public interface InstructionConverter {


    /**
     * Load a line into memory.
     * Override in implementations.
     *
     * @param line      the input line.
     * @param registers the registers.
     * @param ram       the ram.
     */
    default void load(Line line, RamList registers, RamList ram){
        if(line != null && line.getInstruction() != null &&
            line.getAddress() != null){
            int programCounter = ram.getProgramCounter();
            String programCounterStr = RamUtils.INSTANCE.intToString(programCounter);
            ram.write(programCounterStr,
                    line.getInstruction().getNibble() +
                    line.getAddress().getNibble());
            ram.setProgramCounter(programCounter+1);
        }
    }

    /**
     * Apply a line against memory.
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
