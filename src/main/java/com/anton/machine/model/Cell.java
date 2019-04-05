package com.anton.machine.model;

import com.anton.machine.commands.RamWord;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a single byte of memory.
 */
@Getter
@Setter
@Builder(toBuilder = true)
public class Cell {
    private RamWord ramWord;
    private String high;
    private String low;
    private Line line;
}
