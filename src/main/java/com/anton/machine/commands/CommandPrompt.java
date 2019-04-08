package com.anton.machine.commands;

import java.util.Arrays;
import java.util.Scanner;

public enum CommandPrompt {
    INSTANCE;

    private Scanner scanner = new Scanner(System.in);

    /**
     * Present a command prompt to the user.
     */
    public void commandPrompt(){
        Command command = Command.NOOP;
        do {
            System.out.print("mcs> ");
            String line = scanner.nextLine();
            command = Command.find(line);
            if(Command.LOAD.equals(command)){
                try {
                    // Load a mac program into memory.
                    Memory.INSTANCE.resetAndLoad(
                            Loader.INSTANCE.load(
                                    getParams(command, line)));
                } catch(Throwable e){
                    System.out.println("Error: " + e);
                }
            } else if(Command.HELP.equals(command)){
                Helper.INSTANCE.help();
            } else if(Command.MEMORY.equals(command)||
                    Command.MEM.equals(command)){
                Memory.INSTANCE.memory(getParams(command, line));
            } else if(Command.RUN.equals(command)){
                Memory.INSTANCE.run();
            } else if(Command.RESET.equals(command)){
                Memory.INSTANCE.reset();
            } else if(Command.STEP.equals(command)){
                Memory.INSTANCE.step();
            } else if(null == line || line.trim().length() == 0){
                // Do nothing
            } else {
                System.out.println("Unrecognised command: " + line);
                System.out.println("Commands: " + Arrays.asList(Command.values()));
            }

        }while(!Command.QUIT.equals(command));
        System.out.println("mcs> exit program.");
    }

    /**
     * Return the line minus the command.
     *
     * @param command command at the beginning of the line.
     * @param line The line to process.
     * @return the arguments in the line.
     */
    private String getParams(Command command, String line) {
        if(command.getCmdName().length()+2<line.length()){
            return line.substring(command.getCmdName().length()+1);
        }
        return "";
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
