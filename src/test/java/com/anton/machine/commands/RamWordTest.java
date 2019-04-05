package com.anton.machine.commands;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class RamWordTest {

    @Test
    public void shouldRepresent8BitsAsString() {

        final List<String> inputBits = Arrays.asList(
                "11111111",
                "11111110 ",
                "11111101  ",
                "  11111011",
                "   11110111",
                "11101111",
                "  11011111 ",
                "10111111",
                "01111111");

        RamWord ramWord = new RamWord();

        inputBits.forEach(expected -> {
            ramWord.setBinary(expected);
            String actual = ramWord.getBinary();
            Assert.assertEquals(expected.trim(),actual.trim());
            log.debug("Ok -> " + actual);
        });
    }

    @Test
    public void shouldOverflowWhenLarger(){

        final List<String> inputBits = Arrays.asList(
                "1111111111111111",
                "0010000010111111",
                "0111111100000000");

        RamWord ramWord = new RamWord();
        Assert.assertFalse(ramWord.isOverflow());

        for(String expected : inputBits) {
            ramWord.setBinary(expected);
            String actual = ramWord.getBinary();
            Assert.assertTrue(ramWord.isOverflow());
            log.debug("Ok -> " + actual);
        }
        String expected = "1010101";
        ramWord.setBinary(expected);
        String actual = ramWord.getBinary();
        Assert.assertFalse(ramWord.isOverflow());
        log.debug("Ok -> " + actual);

    }
}