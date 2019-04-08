package com.anton.machine.model.convert;

import com.anton.machine.commands.Config;
import com.anton.machine.commands.RamList;
import com.anton.machine.commands.RamUtils;
import com.anton.machine.model.Address;
import com.anton.machine.model.Instruction;
import com.anton.machine.model.Line;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DivConverter implements InstructionConverter {

    /**
     * Apply a line against memory.
     * Override in implementations.
     *
     * @param line      the input line.
     * @param registers the registers.
     * @param ram       the ram.
     */
    @Override
    public void apply(Line line, RamList registers, RamList ram) {
        if (line != null && Instruction.ADD.equals(line.getInstruction())) {
            //read value from reg B
            String valueStrB = registers.read(Address.A0001.getNibble());
            //read value from reg A
            String valueStrA = registers.read(Address.A0000.getNibble());
            //convert B to int
            int valueB = RamUtils.INSTANCE.stringToInt(valueStrB);
            //convert A to int
            int valueA = RamUtils.INSTANCE.stringToInt(valueStrA);
            //divide A by B
            try {
                valueA = valueA / valueB;
            } catch(Exception e ){
                log.error("Error : " + e);
                valueA=0;
            }
            // Set flag if overflow
            if(valueA < Config.INSTANCE.getWordMinValue()){
                registers.setOverflow(Address.A0000.getNibble());
            }
            // Set flag if neg
            if(valueA < 0){
                registers.setNegative(Address.A0000.getNibble());
            }

            //convert value A into string
            valueStrA = RamUtils.INSTANCE.intToString(valueA);
            //write value A to register A
            registers.write(Address.A0000.getNibble(),
                    valueStrA);
        }
    }
}
