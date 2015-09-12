package com.github.klane.euler.problems.problems001_020;

import com.github.klane.euler.JavaProblem;

import java.util.Arrays;

public final class Problem1 extends JavaProblem<Integer> {

    public static final int LIMIT = 1000;
    public static final int[] MULTIPLES = {3, 5};

    @Override
    public Integer get() {
        int result = 0;

        Arrays.sort(MULTIPLES);

        for (int i=MULTIPLES[0]; i<LIMIT; i++) {
            for (int j : MULTIPLES) {
                if (i % j == 0) {
                    result += i;
                    break;
                }
            }
        }

        return result;
    }
}
