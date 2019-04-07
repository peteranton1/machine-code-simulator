package com.anton.machine.commands;

import com.anton.machine.model.Address;
import com.anton.machine.model.Cell;
import com.anton.machine.model.Instruction;
import com.anton.machine.model.Line;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Load a program into memory as a list.
 */
@Slf4j
public enum Memory {
    INSTANCE;

    private RamList registers = new RamList();
    private RamList ram = new RamList();

    public void reset(List<Line> lines){
        registers.clear();
        ram.clear();
        Assembler.INSTANCE.assemble(lines, registers, ram);
    }

    public void memory() {
        System.out.println(formatCell("Address", "Value", "Comment"));
        System.out.println(formatCell("--------", "--------", "-------"));
        for (RamWord ramWord : ram.getList()) {
            System.out.println(ramWord);
        }
        System.out.println(formatCell("--------", "--------", "-------"));
        for (RamWord ramWord : registers.getList()) {
            System.out.println(ramWord);
        }
        System.out.println(formatCell("--------", "--------", "-------"));
    }

    private String formatCell(String s1, String s2, String s3) {
        return String.format("%10s: %8s; %s", s1, s2, s3);
    }
}
