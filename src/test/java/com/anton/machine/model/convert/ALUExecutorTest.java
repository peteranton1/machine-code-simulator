package com.anton.machine.model.convert;

import com.anton.machine.commands.RamList;
import com.anton.machine.commands.RamWord;
import com.anton.machine.model.Address;
import com.anton.machine.model.Argument;
import com.anton.machine.model.Instruction;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@Slf4j
public class ALUExecutorTest {

    public RamList getRegisters(String leftBits, String rightBits){
        RamList registers = new RamList();

        registers.write(Address.A0001.getCode(),leftBits,"Write reg B");
        Assert.assertThat(registers.findOrAdd(Address.A0001.getCode()).readValue(),
                is("0000"+leftBits));
        registers.write(Address.A0000.getCode(),rightBits,"Write reg A");
        Assert.assertThat(registers.findOrAdd(Address.A0000.getCode()).readValue(),
                is("0000"+rightBits));
        return registers;
    }

    @Test
    public void shouldExecuteAdd() {
        AddExecutor executor = new AddExecutor();
        Assert.assertThat(executor.performOperation(1,3),is(4));

        RamList registers = getRegisters("0001", "0011");
        executor.execute(Instruction.ADD, new Argument("01" + "00"),
                "add B and A", registers, null);
        Assert.assertThat(registers.findOrAdd(Address.A0000.getCode()).readValue(),
                is("0000"+"0100"));
        log.debug("ADD Register A OK: " + registers.read(Address.A0000.getCode()));
    }

    @Test
    public void shouldExecuteSub() {
        SubExecutor executor = new SubExecutor();
        Assert.assertThat(executor.performOperation(1,2),is(1));

        RamList registers = getRegisters("0001", "0011");
        executor.execute(Instruction.SUB, new Argument("01" + "00"),
                "sub B and A", registers, null);
        Assert.assertThat(registers.findOrAdd(Address.A0000.getCode()).readValue(),
                is("0000"+"0010"));
        log.debug("SUB Register A OK: " + registers.read(Address.A0000.getCode()));
    }

    @Test
    public void shouldExecuteMult() {
        MultExecutor executor = new MultExecutor();
        Assert.assertThat(executor.performOperation(3,2),is(6));

        RamList registers = getRegisters("0011", "0010");
        executor.execute(Instruction.MULT, new Argument("01" + "00"),
                "mult B and A", registers, null);
        Assert.assertThat(registers.findOrAdd(Address.A0000.getCode()).readValue(),
                is("0000"+"0110"));
        log.debug("MULT Register A OK: " + registers.read(Address.A0000.getCode()));
    }

    @Test
    public void shouldExecuteDiv() {
        DivExecutor executor = new DivExecutor();
        Assert.assertThat(executor.performOperation(3,12),is(4));

        RamList registers = getRegisters("0011", "1100");
        executor.execute(Instruction.DIV, new Argument("01" + "00"),
                "div B and A", registers, null);
        Assert.assertThat(registers.findOrAdd(Address.A0000.getCode()).readValue(),
                is("0000"+"0100"));
        log.debug("DIV Register A OK: " + registers.read(Address.A0000.getCode()));
    }
}