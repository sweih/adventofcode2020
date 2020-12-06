package de.beachboys.aoc2019;

        import de.beachboys.Day;
        import de.beachboys.Util;
        import de.beachboys.aoc2020.RParser;

        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.List;

public class Day12 extends Day {

    public Object part1(List<String> input) {

        RParser rp = new RParser("<x=(.*), y=(.*), z=(.*)>");
        List<Integer[]> p = rp.parseToIntegerArrayList(input, 3, false);

        List<Planet> planets = new ArrayList<>();
        for  (Integer[] ar : p) {
            Planet planet = new Planet(ar[0], ar[1], ar[2]);
            planets.add(planet);
        }

        for (long k=0;k<100;k++) {
            for (int i = 0; i < planets.size(); i++) {
                for (int j = 0; j < planets.size(); j++) {
                    if (j != i)
                        planets.get(i).gravityWith(planets.get(j));
                }
            }
            for (int i=0; i<planets.size(); i++) {
                planets.get(i).recalcPosition();
            }
        }

        long energy = 0;

        for (int i=0; i<planets.size(); i++) {
            energy += planets.get(i).getEnergy();
        }

        return Long.toString(energy);
    }

    public Object part2(List<String> input) {
        RParser rp = new RParser("<x=(.*), y=(.*), z=(.*)>");
        List<Integer[]> p = rp.parseToIntegerArrayList(input, 3, false);

        long fx=0;
        long fy=0;
        long fz=0;

        List<Planet> planets = new ArrayList<>();
        for  (Integer[] ar : p) {
            Planet planet = new Planet(ar[0], ar[1], ar[2]);
            planets.add(planet);
        }

        AxisState x0 = new AxisState(planets,0);
        AxisState y0 = new AxisState(planets, 1);
        AxisState z0 = new AxisState(planets, 2);

        long k = 0;
        while ((fx==0) || (fy==0) || (fz==0)) {
            k++;

            for (int i = 0; i < planets.size(); i++) {
                for (int j = 0; j < planets.size(); j++) {
                    if (j != i)
                        planets.get(i).gravityWith(planets.get(j));
                }
            }

            for (int i=0; i<planets.size(); i++) {
                planets.get(i).recalcPosition();
            }


            if ((fx==0)) {
                if (x0.equals(planets,0)) fx = k;
            }
            if ((fy==0)) {
                if (y0.equals(planets,1)) fy=k;
            }

            if ((fz==0)) {
                if (z0.equals(planets,2)) fz=k;
            }


        }
        if (fx != 0) System.out.print(Long.toString(fx) + " ");
        if (fy != 0) System.out.print(Long.toString(fy) + " ");
        if (fz != 0) System.out.print(Long.toString(fz));

        long res = Util.leastCommonMultiple( Util.leastCommonMultiple(fx,fy), fz);
        return Long.toString(res);

    }

    private Integer[] getAxis(List<Integer[]> list, int axis) {
        Integer[] result = new Integer[list.size()];

        int counter = 0;
        for (Integer[] row: list) {
            counter++;
            result[counter] = row[axis];
        }
        return result;
    }

}

class AxisState{

    Integer[] pos;
    public AxisState(List<Planet> planets, int axis) {
        this.pos = parseCoordinates(planets, axis);
    }

    private Integer[] parseCoordinates(List<Planet> planets, int axis) {
        Integer[] result = new Integer[planets.size()];
        int counter = 0;
        for (Planet row: planets) {
            switch(axis) {
                case 0:
                    result[counter] = row.x;
                    break;
                case 1:
                    result[counter] = row.y;
                    break;
                case 2:
                    result[counter] = row.z;
                    break;
                default:
                    break;
            }
            counter++;
        }
        return result;
    }

    public boolean equals(List<Planet> planets, int axis) {
        Integer[] newPos = this.parseCoordinates(planets, axis);

        //if (! this.velocitiesZero(planets)) return false;

        return Arrays.equals(newPos, this.pos);
    }

    public boolean velocitiesZero(List<Planet> planets) {
        boolean result = false;
        for (Planet planet: planets) {
            result = (result || ((planet.vx==0) && (planet.vy == 0) && (planet.vz == 0)));
        }
        return result;
    }

    public static void print(Integer[] array) {
        for (int i=0; i< array.length; i++) {
            System.out.print(Integer.toString(array[i]));
            System.out.print(",");
        }
        System.out.println("");
    }
}


class Planet {
    int x,y,z;
    int vx,vy,vz;

    public Planet(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;

        this.vx = 0;
        this.vy = 0;
        this.vz = 0;
    }

    public void gravityWith(Planet planet) {
        if (this.x > planet.x) this.vx--;
        if (this.x < planet.x) this.vx++;

        if (this.y > planet.y) this.vy--;
        if (this.y < planet.y) this.vy++;

        if (this.z > planet.z) this.vz--;
        if (this.z < planet.z) this.vz++;
    }

    public void recalcPosition() {
        this.x+=this.vx;
        this.y+=this.vy;
        this.z+=this.vz;
    }

    public long getEnergy() {
        return (Math.abs(this.x) + Math.abs(this.y) + Math.abs(this.z)) * (Math.abs(this.vx) + Math.abs(this.vy) + Math.abs(this.vz));
    }

    public void print() {
        System.out.print("pos = <x=" + Integer.toString(this.x) + ", y=" + Integer.toString(this.y) + ", z=" + Integer.toString(this.z) + ">");
        System.out.println("; vel = <vx=" + Integer.toString(this.vx) + ", vy=" + Integer.toString(this.vy) + ", vz=" + Integer.toString(this.vz) + ">");
    }
}

