package com.anton.machine.commands;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StringUtilsTest {

    @Test
    public void splitterShouldCreateListOfStrings() {

        int[] expectedLengths = {0, 0, 0, 1, 1, 2, 3, 4};
        String[] inputs = {
                "",
                "  ",
                "         ",
                "   some   ",
                "some",
                "   some    test[99]",
                "some test[99]     11111-33333",
                "   some   test[99]        11111-33333 (*&^%$£)"
        };

        for (int test = 0; test < inputs.length; test++) {
            String input = inputs[test];
            int expectedLength = expectedLengths[test];
            List<String> output = StringUtils.splitter(input);
            System.out.println(String.format("Test %2s: '%s' = %s",
                    test, input, output));
            Assert.assertThat(output.size(), is(expectedLength));
        }
    }

    @Test
    public void removeCommentsShouldRemoveComments(){

        int[] expectedLengths = {0, 0, 0, 1, 1, 2, 3, 4};
        String[] inputs = {
                "; some comment",
                ";somecomment",
                ";;/;;;;;;;;;;;         ",
                "   some  ; other ",
                "some;other",
                "   some    test[99] ;99",
                "some test[99]     11111-33333     ;    fred",
                "   some   test[99]        11111-33333 (*&^%$£) ;;;;;;;"
        };

        for (int test = 0; test < inputs.length; test++) {
            String input = inputs[test];
            int expectedLength = expectedLengths[test];
            List<String> output = StringUtils.splitter(input);
            List<String> output2 = StringUtils.removeComments(output);
            System.out.println(String.format("Test %2s: '%s' = %s",
                    test, output2, output));
            Assert.assertThat(output2.size(), is(expectedLength));
        }
    }
}