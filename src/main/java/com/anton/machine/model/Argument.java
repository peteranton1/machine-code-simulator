package com.anton.machine.model;

import com.anton.machine.commands.RamWord;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents a single byte of memory.
 */
@Getter
@ToString
@AllArgsConstructor
public class Argument {
    private String code;
}
