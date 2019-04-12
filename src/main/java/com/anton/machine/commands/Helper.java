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
                "    memory [<bits>]     - Show the memory, all, or specific loc or reg. \n" +
                "    run                 - Run the loaded program. \n" +
                "    step [n]            - Step [n] steps through program, defaults to 1 step\n";
        log.info(help);
    }

    public void helpLoad(){
        final String help =
                "    help                - Display help\n" +
                "    load <filename>     - Load a file containing machine instructions.\n" ;
        log.info(help);
    }
}
