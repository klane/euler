package com.github.klane.euler.problems.problems021_040;

import com.github.klane.euler.JavaProblem;

import java.math.BigInteger;
import java.util.Set;
import java.util.TreeSet;

public final class Problem29 extends JavaProblem<Integer> {

    public static final int START = 2;
    public static final int END = 100;

    @Override
    public Integer get() {
        Set<BigInteger> terms = new TreeSet<>();

        for (int i=START; i<=END; i++) {
            for (int j=START; j<=END; j++) {
                terms.add(BigInteger.valueOf(i).pow(j));
            }
        }

        return terms.size();
    }
}
