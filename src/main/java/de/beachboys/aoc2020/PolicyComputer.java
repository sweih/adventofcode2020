package de.beachboys.aoc2020;

import de.beachboys.IOHelper;

import java.util.ArrayList;
import java.util.List;


enum computeMode  {UND, ODER}

public class PolicyComputer {

    public List<ParsedCommandLine> lines;
    public List<PasswordPolicy> policies;
    public computeMode mode = computeMode.UND;
    private IOHelper io;

  public PolicyComputer(List<String> input, IOHelper io) {
      this.io = io;
      this.lines = new ArrayList<>();
      this.policies = new ArrayList<>();

      for (String line : input) {
          lines.add(new ParsedCommandLine(line, this.io));
      }
  }

  public Integer count() {
      return this.lines.size();
  }

  public ParsedCommandLine getPolicyAt(Integer index) {
      return this.lines.get(index);
  }

  public void registerPolicy(PasswordPolicy policy) {
      this.policies.add(policy);
  }

  public long correctPasswordCount() {
      long successful = 0;
      long failed = 0;

      for (ParsedCommandLine pcl : this.lines) {
         Integer succeeded = 0;
          for (PasswordPolicy policy : this.policies) {
             if (policy.complies(pcl)) succeeded++;
         }
         switch (this.mode) {
              case UND:
                if (succeeded == this.policies.size()) {
                  successful++;
                } else {
                  failed++;
                }
                break;

                case ODER:
                if (succeeded > 0) {
                    successful++;
                } else {
                     failed++;
                 }
                break;
             default:
                 break;
         }

      }
      return successful;
  }

  public String correctPasswordCountStr() {
      return Long.toString(this.correctPasswordCount());
  }

}
