package de.beachboys.aoc2020;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AssemblerTest {

    @Test
    void runProgram() {

        List<String> code = new ArrayList<String>();
        code.add("acc 1");
        code.add("mov bx, ax");
        code.add("acc 15");
        code.add("mov cx,5");
        code.add("mov dx,ax");
        code.add("hlt");
        code.add("mov dx,5");


        Assembler asm = new Assembler(code);
        asm.runProgram();
        assertEquals(asm.ax, 16);
        assertEquals(asm.dx, 16);
        assertEquals(asm.cx, 5);
        assertEquals(asm.bx, 1);
    }

    @Test
    void runProgram2() {

        List<String> code = new ArrayList<String>();
        code.add("acc 1");
        code.add("mov bx,10");
        code.add("mov cx,5");
        code.add("cmp ax,bx");

        Assembler asm = new Assembler(code);
        asm.runProgram();
        assertEquals(asm.ax, 1);
        assertEquals(asm.bx, 10);
        assertEquals(asm.cx, 5);
        assertEquals(asm.cmp, -1);
    }

    @Test
    void runProgram3() {

        List<String> code = new ArrayList<String>();
        code.add("acc 1");
        code.add("mov bx,10");
        code.add("mov cx,5");
        code.add("cmp 10,bx");

        Assembler asm = new Assembler(code);
        asm.runProgram();
        assertEquals(asm.ax, 1);
        assertEquals(asm.bx, 10);
        assertEquals(asm.cx, 5);
        assertEquals(asm.cmp, 0);
    }
}