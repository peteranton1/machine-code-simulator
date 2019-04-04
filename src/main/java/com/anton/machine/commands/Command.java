package com.anton.machine.commands;

public enum Command {
    HELP("help"),
    LOAD("load"),
    MEMORY("memory"),
    NOOP("noop"),
    QUIT("quit"),
    RUN("run"),
    STEP("step");

    private String cmdName;

    /**
     * Constructor.
     *
     * @param cmdName the command.
     */
    Command(String cmdName) {
        this.cmdName = cmdName;
    }

    /**
     * Getter for cmdName field.
     *
     * @return the value of cmdName.
     */
    public String getCmdName() {
        return this.cmdName;
    }

    public static Command find(String line){
        if(line == null){
            return NOOP;
        }
        String tempLine = line.toLowerCase().trim();
        for(Command command: values()){
            if(tempLine.startsWith(command.getCmdName())){
                return command;
            }
        }
        return NOOP;
    }
}
