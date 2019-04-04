package com.anton.machine.commands;

import com.anton.machine.model.Address;
import com.anton.machine.model.Instruction;
import com.anton.machine.model.Line;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    @Getter
    private List<Line> lines;

    public List<Line> load(String filename) {
        try {
            return Files.lines(Paths.get(filename))
                    .map(this::parseLine)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            log.error("Current folder is: " +
                    new File(".").getAbsolutePath());
            throw new PromptException("Error loading " + filename + ": " + e);
        }
        //return Collections.emptyList();
    }

    public Line parseLine(String inputLine) {
        if (inputLine == null ||
                inputLine.trim().length() == 0 ||
                inputLine.trim().charAt(0) == ';') {
            return null;
        }
        String[] tokens = inputLine.split(" ");
        if (tokens.length >= 2) {
            return Line.builder()
                    .instruction(Instruction.parse(tokens[0]))
                    .address(Address.parse(tokens[1]))
                    .inputLine(inputLine)
                    .build();
        } else {
            return Line.builder()
                    .instruction(Instruction.NOOP)
                    .address(Address.A0000)
                    .inputLine(inputLine)
                    .build();
        }
    }
}
