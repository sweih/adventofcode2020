package de.beachboys.aoc2020;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Memory {

    int size;
    int[] memory;
    public int pagesize=1024;

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }


    public Memory(int size) {
        this.size = size;
        reset();
    }

    public int address(int page, int address ) {
        return address + (page * this.pagesize);
    }

    public void load(int address, int[] ints) {
        int le = ints.length;
        for (int i=0;i<(le);i++) {
            memory[(int) (i+address)] = ints[i];
        }
    }

    public void move(int start, int dest, int length) {
      for (int i=0;i<length; i++) {
          memory[dest+i] = memory[start+i];
      }
    }

    public void load(int address, String hexString) {
        Pattern pattern = Pattern.compile("\\$([0-9A-F]{2})");
        Matcher matcher = pattern.matcher(hexString);

        int idx = 0;
        while (matcher.find()) {
            int b = Integer.parseInt(matcher.group(1), 16);
            this.memory[address+idx] = b;
            idx++;
        }
    }

    public void fillMemory(int address, int length, int value) {
        for (int i=address;i<address+length;i++) {
            memory[i] = value;
        }
    }


    public void reset() {
      this.memory = new int[this.size];
      for (int i=0;i<this.size;i++)
          this.memory[i] = 0x00;
    }

    public int get(int page, int offset) {
      return get(address(page, offset));
    }

    public void set(int page, int offset, int value) {
        set(address(page,offset),value);
    }

    public int get(int address) {
        return this.memory[address];
    }

    public void set(int address, int value) {
        this.memory[address] = value;
    }

    public void set(int address, String hex) {
        set(address, Integer.parseInt(hex,16));
    }

    public int countAppearance(int search) {
        int counter = 0;
        for (int i=0;i<this.size;i++) {
            if (memory[i] == search ) counter++;
        }
        return counter;
    }

    public void printMemory(int page, int width) {
        int idx = page * this.pagesize;

        while (idx < ((page+1) * this.pagesize)) {
            System.out.print(String.format("%02X", this.memory[idx]) + " ");
            if ((idx % width) == 0)
                System.out.println("");
            idx++;
        }
    }



}
