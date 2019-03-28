package com.anton.machine;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Interprets a line of machine codee
 */
@Slf4j
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Interpreter {

    @Builder.Default
    private List<Loader.Line> lines = new ArrayList<>();

    /**
     * Interpret a line of machine code and execute it.
     * @param line
     */
    public void interpret(String line){
        try {

        }catch(Throwable e){

        }
    }

    public void load(String filename){
        this.lines = Loader.INSTANCE.load(filename);
    }

    public void run() {

    }

    public void help(){
        final String help = "\nMachine Code Simulator\n" +
                "=======================\n" +
                "Commands:\n" +
                "    help                - Display this message\n" +
                "    load <filename>     - Load a file containing machine instructions.\n" +
                "    list                - Display the program currently loaded.     \n" +
                "    memory              - Show the memory model of the simulation. \n" +
                "    run                 - Run the loaded program. \n" +
                "    step                - Step through the program one line at a time\n";
        log.info(help);
    }
}

