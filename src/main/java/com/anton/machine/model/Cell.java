package com.anton.machine.model;

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
    private String high;
    private String low;
    private Line line;
}
