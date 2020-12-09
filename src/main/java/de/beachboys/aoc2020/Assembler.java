package de.beachboys.aoc2020;
import java.util.List;

public class Assembler {

    public int ax;              // Accumulator
    public int ip;              // Instruction Pointer
    public int bx;              // Base
    public int cx;              // Counter
    public int dx;              // data
    public int cmp;             // comparator -1, 0, 1 or -2 uninitialized

    Memory memory;

    boolean[] lineExecuted;
    boolean HALT;
    boolean looped = false;
    List<String> program;

    public Assembler(List<String> program) {
        this.program = program;
        this.reset();
    }

    public void runProgram() {

        reset();
        while (!this.HALT) {
            this.runLine(program.get(ip));
            if (this.ip == program.size()) this.HALT = true;
        }


    }

    public void fixProgram() {
        for (int i = 0; i < this.program.size(); i++) {
            String original = program.get(i);
            String[] chunks = original.split(" ");
            String command = chunks[0];
            int parameter = Integer.parseInt(chunks[1]);

            if ("jmp".equals(command) || "nop".equals(command)) {
                if ("jmp".equals(command)) {
                    program.set(i, original.replace("jmp", "nop"));
                } else {
                    program.set(i, original.replace("nop", "jmp"));
                }

                this.reset();
                this.runProgram();
                if (!this.looped) {
                    return;
                }
                program.set(i, original);
            }
        }
    }




    public void runLine(String line) {
        String command = "";
        String parameter = "";
        if (line.trim().indexOf(' ')>0) {
            command = line.substring(0, line.indexOf(' ')).trim();
            parameter = line.substring(line.indexOf(' ') + 1).trim();
        } else {
            command = line.trim();
        }
          int currIdx = ip;

        if (lineExecuted[ip]) {
                this.HALT = true;
                this.looped = true;
                return;
        }
        
        switch(command) {
            case "acc":
                this.ax = this.ax + ti(parameter);
                this.ip++;
                break;
            case "jmp":
                jmp(true, ti(parameter), false);
                break;
            case "nop":
                this.ip++;
                break;
            case "mov":
                mov(parameter);
                this.ip++;
                break;
            case "cmp":
                cmp(parameter);
                this.ip++;
                break;
            case "hlt":
                this.HALT = true;
                break;
            default:
                break;
        }
        //this.printCommandline(command, parameter);
        lineExecuted[currIdx] = true;
    }

    private void jmp(boolean condition, int offset, boolean absolute) {
        if (condition) {
            if (absolute) {
                this.ip = offset;
            } else {
                this.ip = this.ip + offset;
            }
        }
    }

    private void cmp(String parameter) {
        String[] pa = tsa(parameter);
        Integer p1 = getValue(pa[0]);
        Integer p2 = getValue(pa[1]);
        cmp = 0;
        if (p1<p2) cmp = -1;
        if (p1>p2) cmp = 1;
    }

    private void mov(String parameter) {
        String[] pa = tsa(parameter);
        if (pa[0].equals("ax")) {
            this.ax = getValue(pa[1]);
        }

        if (pa[0].equals("bx")) {
            this.bx = getValue(pa[1]);
        }

        if (pa[0].equals("cx")) {
            this.cx = getValue(pa[1]);
        }

        if (pa[0].equals("dx")) {
            this.dx = getValue(pa[1]);
        }
    }

    private int getValue(String s) {
        int result = Integer.MAX_VALUE;

        if (isNumeric(s)) {
            result = Integer.parseInt(s);
        } else {
            switch (s) {
                case "ax":
                    result = ax;
                    break;
                case "bx":
                    result = bx;
                    break;
                case "cx":
                    result = cx;
                    break;
                case "dx":
                    result = dx;
                    break;
                default:
                    break;
            }
        }
        return result;
    }

    public static boolean isNumeric(final String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        return str.chars().allMatch(Character::isDigit);
    }


    private int ti(String s) { return Integer.parseInt(s);}

    private String[] tsa(String s) {
        String[] params = s.split(",");
        for(int i=0;i<params.length;i++) {
            params[i] = params[i].trim();
        }
        return params;
    }

    private void reset() {
        ip = 0;
        ax = 0;
        bx = 0;
        cx = 0;
        dx = 0;
        HALT = false;
        cmp = -2;
        memory = new Memory(1024); // 1024 bytes

        looped = false;
        this.lineExecuted = new boolean[program.size()];

    }

    public void printCommandline(String command, Integer parameter) {
        System.out.println(command +" " + parameter.toString() + "|" + this.ax);
    }

}
