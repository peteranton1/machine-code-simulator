package com.anton.machine;

import com.anton.machine.commands.CommandPrompt;
import com.anton.machine.commands.Helper;
import com.anton.machine.commands.Loader;
import com.anton.machine.commands.Memory;

/**
 * Main entry into the application
 */
public class MCS {
    public static void main(String[] args) {
        System.out.println("Machine Code Simulator 1.0");
        if(args.length>0){
            Memory.INSTANCE.resetAndLoad(Loader.INSTANCE.load(args[0]));
            Memory.INSTANCE.memory();
        }else{
            Helper.INSTANCE.help();
            // load src/test/resources/testprog1.mac
            CommandPrompt.INSTANCE.commandPrompt();
        }
    }
}
