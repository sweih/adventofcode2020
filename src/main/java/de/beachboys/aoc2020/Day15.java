package de.beachboys.aoc2020;

import de.beachboys.Day;

import java.util.HashMap;
import java.util.List;

public class Day15 extends Day {

    public Object part1(List<String> input) {

        int data[] = new int[] {12,1,16,3,11,0};
        int cnt = 30000000;

        Spoken[] spoken = new Spoken[100000000]; // arbitrary array size, since I did not want to use lists... May need change

        for (int i=0; i<spoken.length; i++) {
            spoken[i] = new Spoken();
        }

        int spcounter = 1;
        int last = 0;

        for (int i=0; i< data.length;i++) {
          spoken[data[i]].set(spcounter);
          spcounter++;
          last = spcounter;
        }

        while (spcounter <= cnt) {
            if (! spoken[last].empty ) {
                last = spoken[last].minusOne - spoken[last].minusTwo;
            } else {
                last = 0;
            }

            spoken[last].set(spcounter);
            spcounter++;
        }

        return Integer.toString(last);
    }

    public Object part2(List<String> input) {

        return 2;
    }

}

class Spoken {

    public int minusOne = 0;
    public int minusTwo = 0;
    public boolean empty = true;
    public boolean initialized = false;

    public void set(int round) {
        this.minusTwo = this.minusOne;
        this.minusOne = round;
        if (! this.initialized) {
            this.initialized = true;
        } else {
            this.empty = false;
        }
    }


}
