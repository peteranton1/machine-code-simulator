package com.anton.machine.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Utilities for handling Strings.
 */
class StringUtils {


    /**
     * Creates a string list by splitting a string at whitespace.
     *
     * @param input input string.
     * @return list of the different words in the input.
     */
    static List<String> splitter(String input) {
        List<String> output = new ArrayList<>();

        StringBuilder buf = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isWhitespace(c)) {
                if (buf.length() > 0) {
                    output.add(buf.toString());
                    buf.delete(0, buf.length());
                }
            } else {
                buf.append(c);
            }
        }
        if (buf.length() > 0) {
            output.add(buf.toString());
            buf.delete(0, buf.length());
        }

        return output;
    }

    static List<String> removeComments(List<String> inputs){
        List<String> outputs = new ArrayList<>();
        boolean foundSemi = false;
        for(String input: inputs){
            if(input.startsWith(";")){
                foundSemi = true;
            }
            if(!foundSemi){
                outputs.add(input);
            }
        }
        return outputs;
    }
}
