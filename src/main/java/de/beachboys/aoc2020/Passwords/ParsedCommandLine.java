package de.beachboys.aoc2020.Passwords;

import de.beachboys.IOHelper;

public class ParsedCommandLine {

    public long min =0;
    public long max =0;
    public String  password = "";
    private String  check = "";
    private int commandType = 1;

    private IOHelper io;

    public ParsedCommandLine(String line, IOHelper io) {
        this.io = io;
        this.parseLine(line);
    }

    public void parseLine(String line) {
        final String pattern = "-| |:";
        String[] chunks = line.split(pattern);

        if (chunks.length == 5)
            processDay02Input(chunks);
    }

    private void processDay02Input(String[] chunks) {
        this.min = Long.parseLong(chunks[0]);
        this.max = Long.parseLong(chunks[1]);
        this.check = chunks[2].trim();
        this.password = chunks[4].trim();
    }


    public String toString() {
       return "Min=" + this.min + "; Max=" + this.max + "; Password="
               + this.password + "; Checkchar=" + this.check;
    }

    public boolean isCheckerAt(Integer index) {
      return (this.password.charAt(index) == this.checker());
    }

    public char checker() {
        return check.charAt(0);
    }

}
