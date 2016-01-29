package com.github.klane.euler.problems.problems001_020;

import com.github.klane.euler.JavaProblem;

public final class Problem2 extends JavaProblem<Integer> {

    public static final int LIMIT = 4000000;

    @Override
    public Integer get() {
        int prev = 1;
        int next = 2;
        int sum = 0;
        int temp;

        while (next < LIMIT) {
            if (next % 2 == 0) {
                sum += next;
            }

            temp = next;
            next += prev;
            prev = temp;
        }

        return sum;
    }
}
