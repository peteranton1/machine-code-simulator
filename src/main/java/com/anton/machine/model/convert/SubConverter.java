package com.anton.machine.model.convert;

import com.anton.machine.commands.Config;
import com.anton.machine.commands.RamList;
import com.anton.machine.commands.RamUtils;
import com.anton.machine.model.Address;
import com.anton.machine.model.Argument;
import com.anton.machine.model.Instruction;
import com.anton.machine.model.Line;

public class SubConverter implements InstructionConverter {

    /**
     * Execute a line of a program.
     *
     * @param instruction the input instruction.
     * @param argument    the input argument.
     * @param comment     the input comment.
     * @param registers   the registers.
     * @param ram         the ram.
     */
    @Override
    public void execute(Instruction instruction,
                        Argument argument,
                        String comment,
                        RamList registers,
                        RamList ram) {
        if (Instruction.SUB.equals(instruction)) {
            //read value from reg B
            String valueStrB = registers.read(Address.A0001.getCode());
            //read value from reg A
            String valueStrA = registers.read(Address.A0000.getCode());
            //convert B to int
            int valueB = RamUtils.INSTANCE.stringToInt(valueStrB);
            //convert A to int
            int valueA = RamUtils.INSTANCE.stringToInt(valueStrA);
            //sub B from A
            valueA = valueA - valueB;
            // Set flag if overflow
            if(valueA < Config.INSTANCE.getWordMinValue()){
                registers.setOverflow(Address.A0000.getCode());
            }
            // Set flag if neg
            if(valueA < 0){
                registers.setNegative(Address.A0000.getCode());
            }
            //convert value A into string
            valueStrA= RamUtils.INSTANCE.intToString(valueA);
            //write value A to register A
            registers.write(Address.A0000.getCode(),
                    valueStrA, comment);
        }
    }
}
