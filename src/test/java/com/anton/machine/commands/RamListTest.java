package com.anton.machine.commands;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class RamListTest {

    @Test
    public void shouldReadAndWriteToAddress() {

        String[] addresses = {"00010001", "11111111", "00001000"};
        String valueBinary = "00010001";
        RamList ramList = new RamList();

        for(String address : addresses){
            String expected = valueBinary;
            ramList.write(address,valueBinary);
            String actual = ramList.read(address);
            Assert.assertEquals(expected, actual);
            System.out.println("Ok : " + address + ": " + actual);
        }
        Assert.assertEquals(ramList.getSize(), 3);
        System.out.println("Ram Size : " + ramList.getSize() + ": ");
    }
}