package de.beachboys.aoc2020.Passwords;

import de.beachboys.aoc2020.Passwords.ParsedCommandLine;
import de.beachboys.aoc2020.Passwords.PasswordPolicy;

import java.util.ArrayList;
import java.util.List;

public class PolicySet implements PasswordPolicy {

    List<PasswordPolicy> policies;

    public PolicySet() {
      this.policies = new ArrayList<>();
    }

    public void registerPolicy(PasswordPolicy policy) {
        this.policies.add(policy);
    }


    @Override
    public boolean complies(ParsedCommandLine pcl) {
        int succeeded = 0;
        for (PasswordPolicy policy : this.policies) {
            if (policy.complies(pcl)) succeeded++;
        }
        return (succeeded == this.policies.size());
    }
}
