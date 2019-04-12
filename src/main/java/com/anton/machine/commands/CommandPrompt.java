package com.anton.machine.commands;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Scanner;

@Slf4j
public enum CommandPrompt {
    INSTANCE;

    private Scanner scanner = new Scanner(System.in);

    /**
     * Present a command prompt to the user.
     */
    public void commandPrompt() {
        Command command = Command.NOOP;
        do {
            System.out.print("mcs> ");
            String line = scanner.nextLine();
            String[] args = getArgs(line);
            command = Command.find(args[0]);
            if (Command.LOAD.equals(command)) {
                try {
                    if(args.length < 2){
                        Helper.INSTANCE.helpLoad();
                    }else {
                        // Load a mac program into memory.
                        Memory.INSTANCE.resetAndLoad(
                                Loader.INSTANCE.load(args[1]));
                    }
                } catch (Throwable e) {
                    System.out.println("Error: " + e);
                }
            } else if (Command.HELP.equals(command)) {
                Helper.INSTANCE.help();
            } else if (Command.MEMORY.equals(command)) {
                if(args.length < 2){
                    Memory.INSTANCE.memory();
                }else {
                    Memory.INSTANCE.memory(args[1]);
                }
            } else if (Command.RUN.equals(command)) {
                int rows = Memory.INSTANCE.run();
                System.out.println("Program lines run: " + rows);
            } else if (Command.RESET.equals(command)) {
                Memory.INSTANCE.reset();
            } else if (Command.STEP.equals(command)) {
                if(args.length < 2){
                    Memory.INSTANCE.step("1");
                }else {
                    Memory.INSTANCE.step(args[1]);
                }
            } else if (null == line || line.trim().length() == 0 ||
                    Command.QUIT.equals(command)) {
                // Do nothing
            } else {
                System.out.println("Unrecognised command: " + line);
                System.out.println("Commands: " + Arrays.asList(Command.values()));
            }

        } while (!Command.QUIT.equals(command));
        System.out.println("mcs> exit program.");
    }

    private String[] getArgs(String line) {
        return (""+line).split("\\s");
    }

    /**
     * main method to test this class interactively.
     *
     * @param args args.
     */
    public static void main(String[] args) {
        // load src/test/resources/testprog1.mac
        CommandPrompt.INSTANCE.commandPrompt();
    }
}
