package com.anton.machine.commands;

import com.anton.machine.model.Instruction;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@Slf4j
public class MemoryTest {

    public static final String TESTPROG_1 = "src/test/resources/testprog1.mac";

    @Test
    public void resetAndLoad() {
    }

    @Test
    public void reset() {
        Memory.INSTANCE.resetAndLoad(Loader.INSTANCE.load(TESTPROG_1));
        Assert.assertThat(Memory.INSTANCE.ramSize(),is(9));
        Assert.assertThat(Memory.INSTANCE.registersSize(),is(0));
        Memory.INSTANCE.reset();
        Assert.assertThat(Memory.INSTANCE.ramSize(),is(0));
        Assert.assertThat(Memory.INSTANCE.registersSize(),is(0));
    }

    @Test
    public void run() {
        Memory.INSTANCE.resetAndLoad(Loader.INSTANCE.load(TESTPROG_1));
        Assert.assertThat(Memory.INSTANCE.ramSize(),is(9));
        Assert.assertThat(Memory.INSTANCE.registersSize(),is(0));
    }

    @Test
    public void step() {
        Memory.INSTANCE.resetAndLoad(Loader.INSTANCE.load(TESTPROG_1));
        Assert.assertThat(Memory.INSTANCE.ramSize(),is(9));
        Assert.assertThat(Memory.INSTANCE.registersSize(),is(0));
        Instruction instruction = Memory.INSTANCE.step();
        Assert.assertThat(instruction,is(Instruction.LOAD_AI));
        Assert.assertThat(Memory.INSTANCE.getProgramCounter(),is(1));
    }

    @Test
    public void memory() {
        Memory.INSTANCE.resetAndLoad(Loader.INSTANCE.load(TESTPROG_1));
        Assert.assertThat(Memory.INSTANCE.ramSize(),is(9));
        Assert.assertThat(Memory.INSTANCE.registersSize(),is(0));
    }
}