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

    private final Map<Address, Cell> memory = new LinkedHashMap<>();

    public void reset(List<Line> lines){

        int row = 0;
        for(Address programAddress: Address.values()){
            Cell cell = null;
            if(lines.size()>row) {
                Line line = lines.get(row);
                cell = Cell.builder()
                        .high(line.getInstruction().getNibble())
                        .low(line.getAddress().getNibble())
                        .line(line)
                        .build();
            }else {
                cell = Cell.builder()
                        .high(Instruction.NOOP.getNibble())
                        .low(Address.A0000.getNibble())
                        .line(Line.builder()
                                .instruction(Instruction.NOOP)
                                .address(Address.A0000)
                                .inputLine("Noop")
                                .build())
                        .build();
            }
            memory.put(programAddress, cell);
            row++;
        }
    }

    public void memory() {
        System.out.println(formatCell("Addr", "Inst", "Arg", "Mneumonic"));
        System.out.println(formatCell("----", "----", "----", "----"));
        for(Address programAddress: Address.values()){
            show(programAddress);
        }
        System.out.println(formatCell("----", "----", "----", "----"));
    }

    private void show(Address programAddress){
        Cell cell = memory.get(programAddress);
        System.out.println(formatCell(programAddress.getNibble(),
                cell.getHigh(), cell.getLow(),
                cell.getLine().getInstruction().name()));
    }

    private String formatCell(String s1, String s2, String s3, String s4){
        return String.format("%4s: %4s %4s ; %s", s1, s2, s3, s4);
    }
}
