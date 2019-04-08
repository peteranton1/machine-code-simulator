package com.anton.machine.model.convert;

import com.anton.machine.commands.RamList;
import com.anton.machine.commands.RamUtils;
import com.anton.machine.commands.RamWord;
import com.anton.machine.model.Argument;
import com.anton.machine.model.Instruction;
import com.anton.machine.model.Line;

public interface InstructionConverter {


    /**
     * Load a line into memory.
     * Override in implementations.
     *
     * @param line the input line.
     * @param ram  the ram.
     */
    static void load(Line line, RamList ram) {
        if (line != null && line.getInstruction() != null &&
                line.getAddress() != null) {
            int programCounter = ram.getProgramCounter();
            String programCounterStr = RamUtils.INSTANCE.intToString(programCounter);
            Instruction instruction = line.getInstruction();
            String argument = line.getAddress().getCode();
            String code = instruction.getCode();
            String comment = instruction.getComment();
            ram.write(programCounterStr,
                    code + argument,
                    String.format(comment, argument));
            ram.setProgramCounter(programCounter + 1);
        }
    }

    /**
     * Execute a line of a program.
     *
     * @param instruction the input instruction.
     * @param argument    the input argument.
     * @param comment     the input comment.
     * @param registers   the registers.
     * @param ram         the ram.
     */
    void execute(Instruction instruction,
                 Argument argument,
                 String comment,
                 RamList registers,
                 RamList ram);

    /**
     * Process ram using the correct converter.
     *
     * @param ramWord   The ramword to execute.
     * @param registers the registers.
     * @param ram       the ram.
     */
    static Instruction executeStep(RamWord ramWord, RamList registers, RamList ram) {
        if (ramWord == null || registers == null || ram == null ||
                ramWord.readValue() == null) {
            throw new RuntimeException("Error at program line: " + ramWord);
        }
        String codeOrMneumonic = ramWord.readValue();
        Instruction instruction = Instruction.parse(codeOrMneumonic);
        if (Instruction.NOOP.equals(instruction) ||
                Instruction.HALT.equals(instruction) ||
                codeOrMneumonic.length() <= instruction.getCode().length()) {
            System.out.println("Not executing: " + instruction);
            return instruction;
        }
        Argument argument = new Argument(
                codeOrMneumonic.substring(instruction.getCode().length()));
        String comment = ramWord.getComment();
        InstructionConverter converter = Instruction.getConverter(instruction);
        System.out.println(ramWord);
        converter.execute(instruction, argument, comment, registers, ram);
        return instruction;
    }
}
