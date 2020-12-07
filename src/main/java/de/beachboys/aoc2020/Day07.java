package de.beachboys.aoc2020;

import de.beachboys.Day;

import java.util.*;

public class Day07 extends Day {

    public Object part1(List<String> input) {
        for (String line: input) {
          parseRule(line);
        }

        return Long.toString(BagStore.getInstance().getBag("shiny gold bag").countUniqueParents());
    }

    public Object part2(List<String> input) {
        for (String line: input) {
            parseRule(line);
        }

        // We do not want to count the shiny goldbag, so -1
        return Long.toString(BagStore.getInstance().getBag("shiny gold bag").countRequiredBags()-1);
    }

    private void parseRule(String rule) {

        String[] res = rule.split(" contain ");
        String bagId = res[0].replaceAll("bags", "bag");
        Bag ParentBag;
        if (BagStore.getInstance().exists(bagId)) {
          ParentBag = BagStore.getInstance().getBag(bagId);
        }  else {
            ParentBag = new Bag(bagId);
        }

        String[] others = res[1].split(",");
        for (String other: others) {
            String ws = other.replaceAll("\\.", "").replaceAll("bags", "bag").replaceAll("no", "0");

            String[] parts = ws.split(",");
            for (String part : parts) {
                part = part.trim();
                String id = part.substring(1).trim();
                Integer number = Integer.parseInt(part.substring(0,1));
                Bag child = BagStore.getInstance().getBag(id);
                ParentBag.registerChild(child, number);
            }
        }


    }


}

class Bag {
    String id = "";
    List<Integer> numbers;
    List<Bag> childs;
    List<Bag> parents;

    public Bag(String id) {
        this.id = id;

        this.numbers = new ArrayList<>();
        this.childs = new ArrayList<>();
        this.parents = new ArrayList<>();
        BagStore.getInstance().addBag(this);
    }

    public void registerChild(Bag bag, Integer numbers) {
      this.numbers.add(numbers);
      this.childs.add(bag);
      bag.registerParent(this);
    }

    public void registerParent(Bag bag) {
        this.parents.add(bag);
    }


    public void print() {
        System.out.println("-" + id + "-");
    }

    public void printWithChilds() {
        System.out.print("Parent: ");
        print();
        printChilds();;
        System.out.println("------------");
    }

    public void printChilds() {
        int counter = 0;
        for (Bag child : childs) {
            System.out.print(numbers.get(counter).toString() + " ");
            child.print();
            counter++;
        }
    }


    public Set<Bag> getAllPossibleParentBags() {

        Set<Bag> resultSet = new HashSet<>();

        for (Bag bag: this.parents) {
            resultSet.add(bag);
            resultSet.addAll(bag.getAllPossibleParentBags());
        }
        return resultSet;
    }

    public long countUniqueParents() {
        Set<Bag> allParentBags = this.getAllPossibleParentBags();
        return  allParentBags.stream().count();
    }

    // Need to Set -Xss20m as maximum call stack size!!!
    public long countRequiredBags() {
        long result = 0;
        int counter = 0;
        for (Bag bag : this.childs) {
            result += this.numbers.get(counter) * bag.countRequiredBags();
            counter++;
        }

        return (result+1);
    }

}

class BagStore {
    private static final BagStore OBJ = new BagStore();

    HashMap<String,Bag> registry;

    private BagStore() {
        registry = new HashMap<>();
    }

    public void addBag(Bag bag) {
      registry.put(bag.id, bag);
    }

    public boolean exists(String key) {
        return registry.containsKey(key);
    }

    public Bag getBag(String key) {
        if (exists(key)) {
            return registry.get(key);
        } else {
            return new Bag(key);
        }
    }

    public void dump() {
        System.out.println(registry.toString());
    }

    public static BagStore getInstance() {
        return OBJ;
    }
}
