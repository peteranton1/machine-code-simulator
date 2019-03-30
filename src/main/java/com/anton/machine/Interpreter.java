package com.anton.machine;

import com.anton.machine.commands.Helper;
import com.anton.machine.commands.Loader;
import com.anton.machine.commands.Memory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * Interprets a line of machine codee
 */
@Slf4j
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Interpreter {

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
        Memory.INSTANCE.reset(Loader.INSTANCE.load(filename));
    }

    public void memory(){
        Memory.INSTANCE.memory();
    }

    public void run() {

    }

    public void help(){
        Helper.INSTANCE.help();
    }
}

