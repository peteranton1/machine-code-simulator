6502 Architecture
----

# Introductions:

The 6502 family microprocessors belong to the 
category of 8-bit CPU. It only has a few 
internal registers that allow addressing 64Kb 
of memory. The processor is "Little Endian" 
which requires addresses to be represented 
in the format of "least significant byte first".

The 6502 family microprocessors regard the 
first 256 bytes of memory ($0000-$00FF) as 
"Zero Page"."Zero Page" is usually a place 
for storing data for "shorter instructions" 
(which means special addressing method) or 
allow indirect access to the memory. The 
second "256 bytes" of memory ($0100-$01FF) 
is reserved for the stack and system variables.

The very last 6 bytes of memory $FFFA to $FFFF 
store the addresses of the devices.

The 6502 family microprocessors usually have 
six registers:

* one 8-bit accumulator register (A)
* two 8-bit index registers (X and Y)
* an 8-bit processor status register (SR)
* an 8-bit stack pointer (SP)
* and a 16-bit program counter (PC)

# Subpages (8): 

## 6502 Memory Map Register:
## Accumulator Register:
## Index Register:
## Program-counter Registers Register:
## Stack-Pointer Register:
## Status Stack Structure