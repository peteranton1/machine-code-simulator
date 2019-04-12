package com.anton.machine.commands;

import java.util.Arrays;
import java.util.List;

public enum Command {
    HELP("help", "h"),
    LOAD("load", "l"),
    MEMORY("memory", "m", "mem"),
    NOOP("noop"),
    QUIT("quit", "q", "x", "wq", "exit"),
    RESET("reset"),
    RUN("run", "r"),
    STEP("step", "s");

    private List<String> cmdNames;

    /**
     * Constructor.
     *
     * @param cmdName the command.
     */
    Command(String... cmdNames) {
        this.cmdNames = Arrays.asList(cmdNames);
    }

    /**
     * Getter for cmdName field.
     *
     * @return the value of cmdName.
     */
    public String getCmdName() {
        return this.cmdNames.get(0);
    }

    public static Command find(String line) {
        if (line == null) {
            return NOOP;
        }
        String tempLine = line.toLowerCase().trim();
        for (Command command : values()) {
            for (String cmdName : command.cmdNames) {
                if (tempLine.startsWith(cmdName)) {
                    return command;
                }
            }
        }
        return NOOP;
    }
}
