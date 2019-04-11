package com.anton.machine.model.convert;

import com.anton.machine.model.Argument;
import com.anton.machine.model.Register;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExecutorHelperTest {

    @Test
    public void getRegisters() {

        final String regAbits = "00";
        final String regBbits = "01";

        assertArrayEquals(ExecutorHelper.INSTANCE.getRegisters(
                new Argument(regAbits+regBbits)),
                new Register[]{
                        Register.REGISTER_A,
                        Register.REGISTER_B
                });
    }
}