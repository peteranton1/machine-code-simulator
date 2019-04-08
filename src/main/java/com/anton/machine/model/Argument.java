package com.anton.machine.model;

import com.anton.machine.commands.RamWord;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a single byte of memory.
 */
@Getter
@AllArgsConstructor
public class Argument {
    private String code;
}
