package com.anton.machine.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a single line of a machine code program.
 */
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
public class Line {

    private Instruction instruction;
    private Address address;

    private String inputLine;

    @Override
    public String toString(){
        return instruction.getNibble() + " " + address.getNibble() +
                String.format(instruction.getComment(),address.getNibble());
    }
}
