package com.github.klane.euler.problems.problems001_020;

import com.github.klane.euler.JavaProblem;

public final class Problem6 extends JavaProblem<Integer> {

    public static final int LIMIT = 100;

    @Override
    public Integer get() {
        int sum = 0;
        int sumSquare = 0;

        for (int i=1; i<=LIMIT; i++) {
            sum += i;
            sumSquare += i*i;
        }

        return sum*sum - sumSquare;
    }
}
