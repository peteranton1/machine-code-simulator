package com.anton.machine.commands;

import com.anton.machine.model.Address;
import com.anton.machine.model.Instruction;
import com.anton.machine.model.Line;
import lombok.extern.slf4j.Slf4j;

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
public enum Helper {
    INSTANCE;

    public void help(){
        final String help = "Machine Code Simulator 1.0\n" +
                "=======================\n" +
                "Commands:\n" +
                "    help                - Display this message\n" +
                "    load <filename>     - Load a file containing machine instructions.\n" +
                "    memory              - Show the memory model of the simulation. \n" +
                "    run                 - Run the loaded program. \n" +
                "    step                - Step through the program one line at a time\n";
        log.info(help);
    }
}
