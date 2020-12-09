package de.beachboys.aoc2020;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemoryTest {

    Memory memory;
    @BeforeEach
    void setUp() {
        this.memory = new Memory(1024);
        this.memory.reset();
        this.memory.load(0, "$01$02$10$04$05$06$07");
    }

    @Test
    void load() {
        this.memory.load(0, "$01$02$10$04$05$06$07");
        assertEquals(this.memory.get(0), 1);
        assertEquals(this.memory.get(5), 6);
    }

    @Test
    void loadFromArray() {
        this.memory.load(9, new int[] {9,10,11,12,13,14,15});
        assertEquals(this.memory.get(9), 9);
        assertEquals(this.memory.get(14), 14);
        assertEquals(this.memory.get(16), 0);
    }

    @Test
    void move() {
        this.memory.move(3,20,4);
        assertEquals(this.memory.get(20), 4);
        assertEquals(this.memory.get(21), 5);
        assertEquals(this.memory.get(23), 7);
    }

    @Test
    void fillMemory() {
        this.memory.fillMemory(0,32,0);
        assertEquals(this.memory.get(0), 0);
        assertEquals(this.memory.get(1), 0);
        assertEquals(this.memory.get(3), 0);
    }


    @Test
    void set() {
        this.memory.set(1,   155);
        assertEquals(this.memory.get(1), 155);
        this.memory.setPagesize(8);
        this.memory.set(0,4,100);
        this.memory.set(1,4,100);
        assertEquals(this.memory.get(4), 100);
        assertEquals(this.memory.get(12), 100);
        assertEquals(this.memory.get(0,4), 100);
        assertEquals(this.memory.get(1,4), 100);
    }

}