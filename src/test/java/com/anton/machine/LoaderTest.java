package com.anton.machine;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;

@Slf4j
public class LoaderTest {

    @Test
    public void shouldParseFileOk() {
        String filename = "src/test/resources/testprog1.mac";
        List<Loader.Line> lines = Loader.INSTANCE.load(filename);

        lines.forEach(
                line -> {
                    System.out.println("line: " + line);
                });
    }

    @Test
    public void shouldParseLinesOk() {
        List<Loader.Instruction> instructions = Arrays
                .asList(Loader.Instruction.values());

        List<String> inputLines = instructions.stream()
                .map(this::getInstructionString)
                .collect(Collectors.toList());

        List<Loader.Line> outputLines = inputLines.stream()
                .map(line -> Loader.INSTANCE.parseLine(line))
                .collect(Collectors.toList());

        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < inputLines.size(); i++) {
            buf.append(String.format("%2s: %s %s %s\n",
                    i,
                    outputLines.get(i).getInstruction().getNibble(),
                    "0000",
                    outputLines.get(i).getInstruction().name()
                    ));
            Assert.assertThat(outputLines.get(i).getInstruction(), is(instructions.get(i)));
        }
        log.debug("Output:\n{}",buf.toString());
    }

    private String getInstructionString(Loader.Instruction instruction) {
        return instruction.getNibble() + " 0000 " + instruction.name() + " ";
    }
}