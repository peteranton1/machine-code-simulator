package com.anton.machine.model.convert;

import com.anton.machine.commands.RamList;
import com.anton.machine.commands.RamUtils;
import com.anton.machine.commands.RamWord;
import com.anton.machine.model.Address;
import com.anton.machine.model.Instruction;
import org.junit.Test;

public class InstructionConverterTest {

    @Test
    public void shouldExecuteStep() {
        RamList registers = new RamList();
        RamList ram = new RamList();
        RamWord ramWord = ram.findOrAdd(Address.A0000.getCode());
        String argument = "1010";
        for(Instruction instruction: Instruction.values()){
            ramWord.setValue(RamUtils.INSTANCE.stringToInt(
                    instruction.getCode()+argument));
            InstructionConverter.executeStep(ramWord,registers,ram);
        }
    }
}