package com.github.klane.euler.problems.problems001_020;

import com.github.klane.euler.JavaProblem;

public final class Problem9 extends JavaProblem<Integer> {

    public static final int TARGET = 1000;

    @Override
    public Integer get() {
        int a = 1;
        int b = 2;
        double c = 0;

        while (a + b + c != TARGET) {
            c = Math.sqrt(a*a + b*b);

            if (a + b + c > TARGET) {
                a += 1;
                b = a + 1;
            } else if (a + b + c < TARGET) {
                b += 1;
            }
        }

        return a * b * (int) c;
    }
}
