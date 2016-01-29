package com.github.klane.euler.problems.problems021_040;

import com.github.klane.euler.JavaProblem;

public final class Problem39 extends JavaProblem<Integer> {

    public static final int MAX_PERIMETER = 1000;

    @Override
    public Integer get() {
        double c;
        int count;
        int maxCount = 0;
        int maxPerimeter = 0;

        for (int p=1; p<=MAX_PERIMETER; p++) {
            count = 0;

            for (int a=1; a<MAX_PERIMETER/2; a++) {
                for (int b=a+1; b<MAX_PERIMETER/2; b++) {
                    c = Math.sqrt(a*a + b*b);

                    if (c == Math.floor(c) && a+b+c == p) {
                        count++;
                    } else if (a+b+c > p) {
                        break;
                    }
                }
            }

            if (count > maxCount) {
                maxCount = count;
                maxPerimeter = p;
            }
        }

        return maxPerimeter;
    }
}
