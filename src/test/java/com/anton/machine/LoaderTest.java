package com.anton.machine;

import com.anton.machine.commands.Loader;
import com.anton.machine.model.Instruction;
import com.anton.machine.model.Line;
import lombok.extern.slf4j.Slf4j;
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
        List<Line> lines = Loader.INSTANCE.load(filename);
        System.out.println(String.format("%s: %s", "Loading program", filename));
        System.out.println("--------------");
        lines.forEach(
                line -> {
                    System.out.println(String.format("%4s %4s %-12s %s",
                            line.getInstruction().getCode(),
                            line.getAddress().getCode(),
                            line.getInstruction().getMneumonic(),
                            String.format(line.getInstruction().getComment(),line.getAddress().getCode())
                            ));
                });
        System.out.println("--------------");
    }

    @Test
    public void shouldParseLinesOk() {
        List<Instruction> instructions = Arrays
                .asList(Instruction.values());

        List<String> inputLines = instructions.stream()
                .map(this::getInstructionString)
                .collect(Collectors.toList());

        List<Line> outputLines = inputLines.stream()
                .map(Loader.INSTANCE::parseLine)
                .collect(Collectors.toList());

        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < inputLines.size(); i++) {
            buf.append(String.format("%s %s %-10s %s\n",
                    outputLines.get(i).getInstruction().getCode(),
                    "0000",
                    outputLines.get(i).getInstruction().name(),
                    outputLines.get(i).getInstruction().getComment()
            ));
            //Assert.assertThat(outputLines.get(i).getInstruction(), is(instructions.get(i)));
        }
        log.debug("Output:\n" + buf.toString());
    }

    private String getInstructionString(Instruction instruction) {
        return instruction.getCode() + " 0000 " + instruction.name() + " " + instruction.getComment();
    }
}