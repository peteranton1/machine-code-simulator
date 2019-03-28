package com.anton.machine;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Load a program into memory and return as a list.
 */
@Slf4j
public enum Loader {
    INSTANCE;

    @AllArgsConstructor
    public enum Instruction {
        NOOP("0000"),
        LOAD("0001"),
        STORE("0010"),
        ADD("0100"),
        SUBTRACT("0101"),
        MULTIPLY("0110"),
        DIVIDE("0111"),
        JUMP("1100"),
        JUMPIFNEG("1101"),
        JUMPIFOVER("1110"),
        HALT("1111");
        @Getter
        private String nibble;
        public static Instruction parse(String nibble){
            for(Instruction instruction: values()){
                if(instruction.getNibble().equals(nibble)){
                    return instruction;
                }
            }
            return NOOP;
        }
    }

    @Getter
    @Setter
    @ToString
    @Builder
    @AllArgsConstructor
    public static class Line {
        private String line;
        private Instruction instruction;
        private List<String> args;
    }

    public List<Line> load(String filename) {
        try {
            return Files.lines(Paths.get(filename))
                    .map(this::parseLine)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            log.error("Error loading {}: {}", filename, e.getMessage());
        }
        return Collections.emptyList();
    }

    public Line parseLine(String inputLine){
        String[] tokens = inputLine.split(" ");
        if(tokens.length >= 2){
            return Line.builder()
                    .instruction(Instruction.parse(tokens[0]))
                    .args(Arrays.asList(tokens))
                    .line(inputLine)
                    .build();
        }
        return null;
    }
}
