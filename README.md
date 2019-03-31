<h1>machine-code-simulator</h1>
====

A simulator for writing machine code or assembler programs.

<h2>How to Run</h2>

1. Clone or download the repository
2. mvn clean install
3. MCS.cmd


<h2>The Architecture</h2>

The machine described is a hypothetical computer
which has two registers, A and B, and 16 bytes of 
RAM memory. The machine can be programmed using machine
code, which is described in the Instruction Set below.

![Image](doc/memory.png "icon")


<h2>The Instruction Set</h2>

![Image](doc/instructions.png "icon")

The instructions listed all have a four-bit value
in binary and a four bit value for the argument. 

By writing a program with these two nibbles as strings
at the beginning of the line, one can write a program in
machine code. This program can then be loaded and 
run using this simulator.

To make programs easier to write, you can write the same 
program using a mneumonic instead of the binary instruction. 
The simulator can handle either representation. An example of 
both types of program is shown below.
 

<h2>The OpCodes</h2>

* 0000 0000 NOOP       ; No operation
* 0001 0000 LOAD_A     ; Read RAM location into Register A
* 0010 0000 LOAD_B     ; Read RAM location into Register B
* 0011 0000 STORE_A    ; Write register A into RAM location
* 0100 0000 LOAD_AI    ; Load a 4 bit value into Register A
* 0101 0000 SHIFT_AI   ; Shift Register A 4 bits then add another 4 bit value
* 0110 0000 ADD        ; Add two registers, store to second register.
* 0111 0000 SUB        ; Subtract two registers, store to second register.
* 1001 0000 MULT       ; Multiply two registers, store to second register.
* 1010 0000 DIV        ; Divide two registers, store to second register.
* 1011 0000 RESERVED   ; Reserved for future use.
* 1100 0000 JUMP       ; Jump program to new RAM location.
* 1101 0000 JUMP_NEG   ; Jump program if A register negative.
* 1110 0000 JUMP_OVER  ; Jump program if A register overflowed.
* 1111 0000 HALT       ; Stop program.

<h2>Example Machine Code program</h2>
* 0100 1010 LOAD_AI    ; Load a 4 bit value 1010 into Register A
* 0011 1110 STORE_A    ; Write register A into RAM location 14
* 0101 1010 SHIFT_AI   ; Shift Register A 4 bits then add another 4 bit value 1010
* 0011 1111 STORE_A    ; Write register A into RAM location 15
* 0010 1110 LOAD_B     ; Read RAM location 14 into Register B
* 0111 0100 SUB        ; Subtract two registers, store to second register.
* 1101 1000 JUMP_NEG   ; Jump program if A register negative to RAM location 8.
* 1100 0101 JUMP       ; Jump program to RAM location 5.
* 1111 0000 HALT       ; Stop program.

<h2>Example Assembler program</h2>
* LOAD_AI 1010     ; Load a 4 bit value 1010 into Register A
* STORE_A 1110     ; Write register A into RAM location 14
* SHIFT_AI 1010    ; Shift Register A 4 bits then add another 4 bit value 1010
* STORE_A 1111     ; Write register A into RAM location 15
* LOAD_B 1110      ; Read RAM location 14 into Register B
* SUB 0100         ; Subtract two registers, store to second register.
* JUMP_NEG 1000    ; Jump program if A register negative to RAM location 8.
* JUMP 0101        ; Jump program to RAM location 5.
* HALT 0000        ; Stop program.

