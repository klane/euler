package com.github.klane.euler.problems.problems021_040;

import com.github.klane.euler.JavaProblem;

import java.util.Arrays;

public final class Problem22 extends JavaProblem<Integer> {

    @Override
    public Integer get() {
        String[] names;
        int totalScore = 0;
        int score;

        try {
            names = super.readFile().get(0).replaceAll("\"", "").split(",");
            Arrays.sort(names);
        } catch (NullPointerException e) {
            return null;
        }

        for (int i=0; i<names.length; i++) {
            score = 0;

            for (int j=0; j<names[i].length(); j++) {
                score += names[i].charAt(j) - 'A' + 1;
            }

            totalScore += score * (i+1);
        }

        return totalScore;
    }
}
