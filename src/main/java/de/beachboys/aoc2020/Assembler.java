package de.beachboys.aoc2020;
import java.util.List;

public class Assembler {

    public int accumulator=0;
    int idx=0;
    Boolean[] lineExecuted;
    boolean stopSignal = false;
    boolean looped = false;
    List<String> program;

    public Assembler(List<String> program) {
        this.lineExecuted = new Boolean[0];
        this.program = program;
    }

    public void runProgram() {

        reset();
        while (!this.stopSignal) {
            this.runLine(program.get(idx));
            if (this.idx == program.size()-1) this.stopSignal = true;
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
        String[] chunks = line.split(" ");
        String command = chunks[0];
        int parameter = Integer.parseInt(chunks[1]);
        int currIdx = idx;

        if (lineExecuted[idx]) {
                this.stopSignal = true;
                this.looped = true;
                return;
        }
        
        switch(command) {
            case "acc":
                this.accumulator = this.accumulator + parameter;
                this.idx++;
                break;
            case "jmp":
                this.idx = this.idx + parameter;
                break;
            case "nop":
                this.idx++;
                break;
            default:
                break;
        }
        //this.printCommandline(command, parameter);
        lineExecuted[currIdx] = true;
    }

    private void reset() {
        idx=0;
        accumulator=0;
        looped = false;
        stopSignal = false;

        this.lineExecuted = new Boolean[program.size()];
        for (int i=0; i<lineExecuted.length;i++) {
            lineExecuted[i] = false;
        }

    }

    public void printCommandline(String command, Integer parameter) {
        System.out.println(command +" " + parameter.toString() + "|" + this.accumulator);
    }

}
